package com.sparkss.base;

import com.sparkss.base.keys.CommonOptionKey;
import com.sparkss.base.keys.SparkOptionEnum;
import com.sparkss.base.pool.ReaderObjPool;
import com.sparkss.base.pool.WriterObjPool;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.sources.v2.DataSourceOptions;
import org.apache.spark.sql.types.StructType;

import java.util.List;

/**
 * 封装属性，在 DataSource 各层级对象之间传递
 * @Author $ zho.li
 * @Date 2020/12/23 11:43
 **/
public class FlowBean {

    /** 表名 */
    private String tableName;
    /** 字符串schema（"`name` STRING,`score` STRING"） */
    private String schemaStr;

    /**
     * 无类型的属性字段
     */
    private String fields;

    /**
     * hbase rowKey
     */
    private String rowKey;

    /** hbase 列族列名（"cf:name,cf:score"） */
    private String hbaseCfCc;
    /** 数据库标识 @{link DataBaseMark} */
    private String dataBaseModeMark;
    /** 各字段schema */
    private StructType schema;
    /** 已传入 sparksql 中的所有option */
    private DataSourceOptions options;
    /** jobId */
    private String jobId;
    /** saveMode */
    private SaveMode saveMode;
    /** schema 中允许的列名(hbase 列剪枝) */
    private List<String> requiredSchemaList;

    /** reader 对象池 class 对象 */
    private Class<? extends WriterObjPool> writerPoolClz;
    /** writer 对象池 class 对象 */
    private Class<? extends ReaderObjPool> readerPoolClz;

    public String getTableName() {
        if (tableName != null && tableName.length() > 0){
            return tableName;
        }
        return options.get(CommonOptionKey.TABLE_NAME).get();
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchemaStr() {
        if (schemaStr != null && schemaStr.length() > 0)
            return schemaStr;
        return options.get(CommonOptionKey.SCHEMA_STR).get();
    }

    public void setSchemaStr(String schemaStr) {
        this.schemaStr = schemaStr;
    }

    public String getFields() {
        if (fields != null && fields.length() > 0)
            return fields;
        return options.get(SparkOptionEnum.FIELDS.getKey()).get();
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    /**
     * 获取 hbase region 的个数，默认去 8 个
     * @return
     */
    public int getHbaseRegions(){
        return options.getInt(SparkOptionEnum.HBASE_REGIONS.getKey(), 8);
    }

    public String getRowKey() {
        if (rowKey != null && rowKey.length() > 0)
            return rowKey;
        return options.get(CommonOptionKey.HBASE_ROW_KEY).get();
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getHbaseCfCc() {
        if (hbaseCfCc != null && hbaseCfCc.length() > 0)
            return hbaseCfCc;
        return options.get(CommonOptionKey.FAMILY_COLUMN).get();
    }

    public void setHbaseCfCc(String hbaseCfCc) {
        this.hbaseCfCc = hbaseCfCc;
    }

    public String getDataBaseModeMark() {
        return dataBaseModeMark;
    }

    public void setDataBaseModeMark(String dataBaseModeMark) {
        this.dataBaseModeMark = dataBaseModeMark;
    }

    public StructType getSchema() {
        return schema;
    }

    public void setSchema(StructType schema) {
        this.schema = schema;
    }

    public DataSourceOptions getOptions() {
        return options;
    }

    public void setOptions(DataSourceOptions options) {
        this.options = options;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public SaveMode getSaveMode() {
        return saveMode;
    }

    public void setSaveMode(SaveMode saveMode) {
        this.saveMode = saveMode;
    }
    public List<String> getRequiredSchemaList() {
        return requiredSchemaList;
    }

    public void setRequiredSchemaList(List<String> requiredSchemaList) {
        this.requiredSchemaList = requiredSchemaList;
    }

    public Class<? extends WriterObjPool> getWriterPoolClz() {
        return writerPoolClz;
    }

    public void setWriterPoolClz(Class<? extends WriterObjPool> writerPoolClz) {
        this.writerPoolClz = writerPoolClz;
    }

    public Class<? extends ReaderObjPool> getReaderPoolClz() {
        return readerPoolClz;
    }

    public void setReaderPoolClz(Class<? extends ReaderObjPool> readerPoolClz) {
        this.readerPoolClz = readerPoolClz;
    }
}
