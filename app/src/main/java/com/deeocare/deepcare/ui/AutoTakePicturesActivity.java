package com.deeocare.deepcare.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.deeocare.deepcare.Contast;
import com.deeocare.deepcare.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 描述：自动连续拍照
 * 开发者：开发者的乐趣JRT
 * 创建时间：2017-3-15 19:16
 * CSDN地址：http://blog.csdn.net/Jiang_Rong_Tao/article
 * E-mail：jrtxb520@163.com
 **/
public class AutoTakePicturesActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private static final String TAG = "AutoTakePicturesActivity";
    private SurfaceView mSurfaceView;
    private Camera mCamera;
    private SurfaceHolder mSurfaceViewHolder;
    private int mPictureCount = 3;//默认自动拍一张
    private final int mPictureCountMax = 30;//默认自动拍一张
    int pic_count = 0;
    private ArrayList<String> mPicturePaths;
    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_take_pictures);
        init();
    }

    private void init() {
        mPicturePaths = new ArrayList<String>();
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        //获取SurfaceHolder
        mSurfaceViewHolder = mSurfaceView.getHolder();
        mSurfaceViewHolder.addCallback(this);
        //触摸屏幕完成对焦
        mSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.autoFocus(null);
                //平滑的缩放：取值在0~mCamera.getParameters().getMaxZoom()
//                 mCamera.startSmoothZoom(2);
            }
        });
    }

    private void startCapture() {
        if (mCamera != null) {
            final Camera.Parameters parameters = mCamera.getParameters();

//            //设置拍照的图片格式
//            parameters.setPictureFormat(PixelFormat.JPEG);
//            //设置图片的预览大小
//            parameters.setPreviewSize(1024, 768);
//            parameters.setPictureSize(1024,768);
//            //设置自动对焦
//            parameters.setAutoExposureLock(true);
//            parameters.setColorEffect(Camera.Parameters.EFFECT_SEPIA);
//            parameters.setExposureCompensation(2);
//            parameters.setAutoWhiteBalanceLock(true);
//            parameters.setZoom(40);
//            parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_INCANDESCENT);
//            parameters.setSceneMode(Camera.Parameters.SCENE_MODE_THEATRE);
//            parameters.set("iso-speed",20);
//            mCamera.setParameters(parameters);
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @SuppressLint("LongLogTag")
                @Override
                public void onAutoFocus(boolean success, final Camera camera) {
                    //动态对对焦成功后，获取拍摄的图片
                    if (success) {
                        float[] distance = new float[3];
                        parameters.getFocusDistances(distance);
                        Log.e(TAG, "distance : " + distance[0] + "," + distance[1] + "," + distance[2]);

                        camera.takePicture(null, null, mPictureCallback);

                        logCameraMessage();
                    }else {
                        setStartPreview(mCamera, mSurfaceViewHolder);

                        Log.e(TAG+"err","对焦失败");
                    }
                }
            });
        }
    }


    /**
     * 拍摄成功后对图片的处理
     */
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @SuppressLint("LongLogTag")
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                FileOutputStream fileOutputStream = null;
                try {
                    File file = new File(Environment.getExternalStorageDirectory()
                            , getPhotoFileName());
                    mPath = file.getAbsolutePath();
                    Log.i(TAG, "onPictureTaken: " + mPath);
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AutoTakePicturesActivity.this, "图片保存失败", Toast.LENGTH_SHORT).show();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            if (mPictureCount > mPictureCountMax) {
                                Toast.makeText(AutoTakePicturesActivity.this, "为了节约内存，连拍张数不要超过" + mPictureCountMax + "张", Toast.LENGTH_SHORT).show();
                            } else {
                                if (++pic_count < mPictureCount) {
                                    //连拍三张
                                    mPicturePaths.add(mPath);
                                    setStartPreview(mCamera, mSurfaceViewHolder);
                                } else {

                                    mPicturePaths.add(mPath);//最后一张图片加入集合
                                    Intent intent = new Intent().putStringArrayListExtra(Contast.PICTURE_PATHS, mPicturePaths);
                                    setResult(RESULT_OK, intent);
                                    fileOutputStream.close();
                                    //保证最后一张图片加入集合并优化用户体验
                                    SystemClock.sleep(2000);
                                    finish();
                                }
                                Toast.makeText(AutoTakePicturesActivity.this, "图片保存成功" + pic_count + "张", Toast.LENGTH_SHORT).show();
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                Toast.makeText(AutoTakePicturesActivity.this, "SD不存在，图片保存失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * 获取Camera对象
     *
     * @return
     */
    @SuppressLint("LongLogTag")
    private Camera getCamera() {
        mCamera = Camera.open();
        Log.e(TAG, "---"+mCamera.getParameters().flatten());
        return mCamera;
    }

    private void logCameraMessage() {
        Camera.Parameters parameters = mCamera.getParameters();
        Log.e(TAG, "\r\n自动曝光锁定的状态: " + parameters.getAutoExposureLock() + "自动白平衡锁的状态:" + parameters.getAutoWhiteBalanceLock() + "\r\n" +
                        "颜色效果:" + parameters.getColorEffect() + "支持的颜色效果:" + parameters.getSupportedColorEffects() + "\r\n" +
                        "闪光模式:" + parameters.getFlashMode() + "支持的闪光模式:" + parameters.getSupportedFlashModes() + "\r\n" +
                        "最大对焦区域:" + parameters.getMaxNumFocusAreas() + "\r\n" +
                        "对焦模式:" + parameters.getFocusMode() + "支持的对焦模式:" + parameters.getSupportedFocusModes() + "\r\n" +
                        "预览窗口大小:" + parameters.getPreviewSize().width + "," + parameters.getPreviewSize().height + "\r\n" +
                        "前场景模式:" + parameters.getSceneMode() + "支持的前场景模式:" + parameters.getSupportedSceneModes() + "\r\n" +
                        "返回图片的图像格式:" + parameters.getPictureFormat() + "支持的图像格式:" + parameters.getSupportedPictureFormats() + "\r\n" +
                        "图片的尺寸:" + parameters.getPictureSize().width + "," + parameters.getPictureSize().height + "\r\n" +
                        "JPEG图片的质量:" + parameters.getJpegQuality() + "Jpeg图片中EXIF缩略图的质量:" + parameters.getJpegThumbnailQuality() + "略图的尺寸:" + parameters.getJpegThumbnailSize().width + "," + parameters.getJpegThumbnailSize().height + "\r\n" +
                        "曝光补偿指数:" + parameters.getExposureCompensation() + "最大曝光补偿指数:" + parameters.getMaxExposureCompensation() + "最小曝光补偿指数" + parameters.getMinExposureCompensation() + "\r\n" +
                        "焦距:" + parameters.getFocalLength() + "\r\n" +
                        "最大检测面数:" + parameters.getMaxNumDetectedFaces() + "\r\n" +
                        "iso:" + parameters.get("iso") + "\r\n" +
                        "zoom:" + parameters.getZoom() +" zoom ratio"+parameters.getZoomRatios()+ "\r\n" +
                        "最大测光区域数:" + parameters.getMaxNumMeteringAreas()


//                        "焦点的距离:"+parameters.getFocusDistances(new float[3])+"支持的最小和最大预览fps:"+parameters.getSupportedPreviewFpsRange()
        );
//
//        List<Camera.Size> picture_size = parameters.getSupportedPictureSizes();
//        Log.e(TAG, "照片支持的尺寸");
//        for (Camera.Size item : picture_size) {
//            Log.e(TAG, item.width + "," + item.height);
//        }
//        List<Camera.Size> supportsize = parameters.getSupportedJpegThumbnailSizes();
//        Log.e(TAG, "缩略图支持的尺寸");
//        for (Camera.Size item : supportsize) {
//            Log.e(TAG, item.width + "," + item.height);
//        }
//        List<Camera.Size> supporPreSize = parameters.getSupportedPreviewSizes();
//        Log.e(TAG, "支持的预览窗口大小");
//        for (Camera.Size item : supporPreSize) {
//            Log.e(TAG, item.width + "," + item.height);
//        }
    }

    /**
     * 设置并且开启相机预览
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder) {
        try {
            //将Camera与SurfaceView开始绑定
            camera.setPreviewDisplay(holder);
            //调整拍摄的方向（默认横屏）
            camera.setDisplayOrientation(90);//旋转90度
            //开启预览
            camera.startPreview();
            startCapture();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放Camera资源
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);//取消回调
            stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * 停止取景
     */
    private void stopPreview() {
        mCamera.stopPreview();
    }

    /**
     * 将Camera和Activity的生命周期绑定
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCamera();
            if (mSurfaceViewHolder != null) {
                setStartPreview(mCamera, mSurfaceViewHolder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }


    /**
     * SurfaceHolder 的回调处理
     *
     * @param surfaceHolder
     */
    @SuppressLint("LongLogTag")
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "surfaceCreated: ");
        setStartPreview(mCamera, mSurfaceViewHolder);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.i(TAG, "surfaceChanged: ");
        stopPreview();//先停止取景，再重新打开
        setStartPreview(mCamera, mSurfaceViewHolder);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "surfaceDestroyed: ");
        releaseCamera();
    }

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
