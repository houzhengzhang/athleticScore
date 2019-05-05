package athletic.domain;
import java.sql.*;

/**
 * competitionField 实体类
 */ 


public class CompetitionField {
	private String fieldId;
	private String name;
	private String address;
	private String state;

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}

