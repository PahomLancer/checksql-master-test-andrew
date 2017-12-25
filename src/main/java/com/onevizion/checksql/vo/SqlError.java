package com.onevizion.checksql.vo;

public class SqlError {

    public static final String SELECT_ERR_TYPE = "SELECT";
    public static final String LINE_DELIMITER = "\r\n";

    private String tableName;
    private String sqlColName;
    private String entityId;
    private String query;
    private String entityIdColName;
    private String errMsg;
    private String errType;
    private String originalQuery;

    private int phase = -1;
    private int table = -1;
    private int row = -1;

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder(LINE_DELIMITER);
        msg.append("==========================");
        msg.append(LINE_DELIMITER);
        msg.append("[Phase=");
        msg.append(phase);
        msg.append(",table=");
        msg.append(table);
        msg.append(",row=");
        msg.append(row);
        msg.append("][");
        msg.append(errType);
        msg.append("][");
        msg.append(tableName);
        if (sqlColName != null && (!sqlColName.trim().isEmpty())) {
            msg.append(".");
            msg.append(sqlColName);
        }
        msg.append("][");
        if (entityIdColName != null && (!entityIdColName.trim().isEmpty())) {
            msg.append(entityIdColName);
            msg.append("=");
            if (entityId != null && (!entityId.trim().isEmpty())) {
                msg.append(entityId);
            }
            msg.append("][");
        }
        msg.append(errMsg);
        msg.append("]");
        if (originalQuery != null && (!originalQuery.trim().isEmpty())) {
            msg.append("[");
            msg.append(originalQuery);
            msg.append("]");
        }
        msg.append(LINE_DELIMITER);

        boolean isGenerateUpdateScript = tableName != null
                && (!tableName.trim().isEmpty()) && sqlColName != null && (!sqlColName.trim().isEmpty())
                && entityIdColName != null && (!entityIdColName.trim().isEmpty()) && entityId != null
                && (!entityId.trim().isEmpty());
        if (isGenerateUpdateScript) {
            msg.append("~~~~~~~~~~~~~~~~~~~");
            msg.append(LINE_DELIMITER);
            msg.append("set define off");
            msg.append(LINE_DELIMITER);
            msg.append("declare");
            msg.append(LINE_DELIMITER);
            msg.append("  v_var varchar2(30000) := q'[]';");
            msg.append(LINE_DELIMITER);
            msg.append("begin");
            msg.append(LINE_DELIMITER);
            msg.append("  update ");
            msg.append(tableName);
            msg.append(" set ");
            msg.append(sqlColName);
            msg.append(" = v_var");
            msg.append(LINE_DELIMITER);
            msg.append("  where ");
            msg.append(entityIdColName);
            msg.append(" = ");
            msg.append(entityId);
            msg.append(";");
            msg.append(LINE_DELIMITER);
            msg.append("end;");
            msg.append(LINE_DELIMITER);
            msg.append("/");
            msg.append(LINE_DELIMITER);
            msg.append("commit;");
            msg.append(LINE_DELIMITER);
            msg.append(LINE_DELIMITER);
            msg.append("select ");
            msg.append(sqlColName);
            msg.append(LINE_DELIMITER);
            msg.append("from ");
            msg.append(tableName);
            msg.append(LINE_DELIMITER);
            msg.append("where ");
            msg.append(entityIdColName);
            msg.append(" = ");
            msg.append(entityId);
            msg.append(";");
            msg.append(LINE_DELIMITER);
        }
        msg.append("==========================");
        return msg.toString();
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getOriginalQuery() {
        return originalQuery;
    }

    public void setOriginalQuery(String originalQuery) {
        this.originalQuery = originalQuery;
    }

    public SqlError(String errType) {
        this.errType = errType;
    }

    public String getSqlColName() {
        return sqlColName;
    }

    public void setSqlColName(String sqlColName) {
        this.sqlColName = sqlColName;
    }

    public String getEntityIdColName() {
        return entityIdColName;
    }

    public void setEntityIdColName(String entityIdColName) {
        this.entityIdColName = entityIdColName;
    }

    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
