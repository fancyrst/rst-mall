package com.rstang.common.sao.impl;

import com.rstang.common.dto.FileInfoDTO;
import com.rstang.common.dto.FileResultDTO;
import com.rstang.common.sao.FileOperateSao;
import com.rstang.util.DateUtils;
import com.rstang.util.io.FileCopyUtils;
import com.rstang.util.validate.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by yeyx on 2017/9/6.
 */
@Component
public class FileOperateSaoImpl implements FileOperateSao {

    @Value("rstang.saveFile.rootPath")
    private String saveFileRootPath;

    private Logger logger = LoggerFactory.getLogger(FileOperateSaoImpl.class);

    @Override
    public FileResultDTO uploadFile(FileInfoDTO infoDTO) {

        FileResultDTO resultDTO = new FileResultDTO();
        if (infoDTO.getAppCode() == null || infoDTO.getFileBytes() == null || ValidateUtils.isEmpty(infoDTO.getFileName())) {
            FileResultDTO.FileResult fileResult = FileResultDTO.FileResult.FAIL;
            fileResult.setResultDesc("参数校验不通过，appCode，fileName,fileBytes不能为空");
            resultDTO.setResult(fileResult);
            return resultDTO;
        }
        String currentMoth = DateUtils.getCurrentTimeByFmt(DateUtils.DATE_YYYYMM);
        //路径: /mall/image/goods/201701/
        StringBuffer filePath = new StringBuffer(infoDTO.getAppCode().getCode())
        .append(File.separator).append(infoDTO.getFileType().getType())
        .append(File.separator).append(infoDTO.getModule().getName())
        .append(File.separator).append(currentMoth).append(File.separator);

        StringBuffer saveFilePath = new StringBuffer(saveFileRootPath).append(File.separator).append(filePath.toString());
        File file = new File(saveFilePath.toString());
        if (!file.exists()) {
            file.mkdirs();
        }

        FileResultDTO.FileResult fileResult = FileResultDTO.FileResult.FAIL;
        try {
            FileOutputStream out = new FileOutputStream(saveFilePath + File.separator + infoDTO.getFileName());
            FileCopyUtils.copy(infoDTO.getFileBytes(), out);
            fileResult = FileResultDTO.FileResult.OK;
        } catch (Exception e) {
            logger.error("FileOperateSaoImpl.uploadFile", e);
            fileResult = FileResultDTO.FileResult.FAIL;
            fileResult.setResultDesc("上传失败：" + e);
            resultDTO.setResult(fileResult);
        }

        resultDTO.setFileName(infoDTO.getFileName());
        resultDTO.setFilePath(filePath.toString() + infoDTO.getFileName());
        resultDTO.setResult(fileResult);
        return resultDTO;
    }

    @Override
    public byte[] getFileBytes(String filePath) {
        return new byte[0];
    }

    @Override
    public String getFileURI(String filePath) {
        return null;
    }

    @Override
    public boolean deleteFile(String filePath) {
        return false;
    }
}
