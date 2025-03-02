//package com.videodanmaku.video.domain.ffmpeg;
//
//import net.bramp.ffmpeg.FFmpeg;
//
//import java.io.File;
//import java.nio.file.Paths;
//
//public class Test {
//    private void processVideo(String inputPath, String originalFileName) {
//        String outputDir = "D:/video/processed/";
//        File dir = new File(outputDir);
//        if (!dir.exists()) dir.mkdirs();
//
//        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
//
//        // 定义多种分辨率
//        String[] resolutions = {"240p", "480p", "720p"};
//        String[] scales = {"426:240", "854:480", "1280:720"};
//        String[] bitrates = {"500k", "1000k", "2000k"};
//
//        // 异步执行转码
//        new Thread(() -> {
//            // 配置FFmpeg路径
//            FFmpeg ffmpeg = FFmpeg.atPath(Paths.get("D:/ffmpeg/bin/"));
//
//            for (int i = 0; i < resolutions.length; i++) {
//                String outputPath = outputDir + baseName + "_" + resolutions[i] + ".mp4";
//
//                try {
//                    ffmpeg
//                            .addInput(UrlInput.fromPath(Paths.get(inputPath))) // 输入文件
//                            .addArguments("-vf", "scale=" + scales[i])       // 分辨率缩放
//                            .addArguments("-c:v", "libx264")                // 视频编码
//                            .addArguments("-b:v", bitrates[i])              // 比特率
//                            .addArguments("-c:a", "aac")                    // 音频编码
//                            .setOutput(outputPath)                          // 输出文件
//                            .setOverwriteOutput(true)                       // 覆盖文件
//                            .execute();                                     // 执行转码
//
//                    System.out.println(resolutions[i] + "转码成功");
//                } catch (Exception e) {
//                    System.err.println(resolutions[i] + "转码失败: " + e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//}
