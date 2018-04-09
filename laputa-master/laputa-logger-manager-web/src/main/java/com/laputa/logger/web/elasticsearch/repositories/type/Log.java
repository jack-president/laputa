package com.laputa.logger.web.elasticsearch.repositories.type;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * Created by JiangDongPing on 2018/04/08.
 */
@Document(indexName = "logstash-#{com.laputa.logger.web.elasticsearch.repositories.type.Log.date}", type = "doc", shards = 1, replicas = 0, refreshInterval = "-1")
public class Log implements Serializable {

    private String message;

    private String logdate;

    private String transId;

    private String loglevel;

    private String threadName;

    private String javaclass;

    private String data;

    private String detail;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogdate() {
        return logdate;
    }

    public void setLogdate(String logdate) {
        this.logdate = logdate;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(String loglevel) {
        this.loglevel = loglevel;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getJavaclass() {
        return javaclass;
    }

    public void setJavaclass(String javaclass) {
        this.javaclass = javaclass;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
