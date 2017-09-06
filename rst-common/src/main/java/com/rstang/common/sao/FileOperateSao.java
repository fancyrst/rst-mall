package com.rstang.common.sao;

import com.rstang.common.dto.FileInfoDTO;
import com.rstang.common.dto.FileResultDTO;

/**
 * 文件操作SAO
 * Created by yeyx on 2017/9/5.
 */
public interface FileOperateSao {

    /**
     * 上传文件
     * @param fileInfoDTO
     * @return
     */
    public FileResultDTO uploadFile(FileInfoDTO fileInfoDTO);

    /**
     * 删除文件
     * @param filePath 文件相对路径，如：/mall/editor/201701/img20170102093321001.jpg
     * @return true 删除成功 false 删除失败
     */
    public boolean deleteFile(String filePath);

    /**
     * 获取文件访问URI
     * @param filePath 文件相对路径，如：/mall/editor/201701/img20170102093321001.jpg
     * @return 目标文件http全路径， 如：http://rstang.com/mall/editor/201701/img20170102093321001.jpg
     */
    public String getFileURI(String filePath);

    /**
     * 获取文件的字节数组
     * @param filePath 文件相对路径
     * @return null表示查找不到文件
     */
    public byte[] getFileBytes(String filePath);

    /**
     * 文件分类
     */
    enum FileType {
        IMG("image", "图片文件"), TXT("txt", "文本文件"), DOC("doc", "办公文件");

        private String type;
        private String desc;

        FileType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * 静态文件分模块管理
     */
    enum BelongModule {
        GOODS("goods", "商品图片"), AD("ad", "广告素材");

        private String name;
        private String desc;

        BelongModule(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
        public String getDesc() {
            return desc;
        }

        public String getName() {
            return name;
        }
    }

}
