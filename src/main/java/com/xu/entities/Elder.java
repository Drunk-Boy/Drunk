package com.xu.entities;


public class Elder {
    private Integer id;
    //老人姓名
    private String elderName;
    //性别
    private Integer gender;
    //民族
    private String nation;
    //年龄
    private Integer age;
    //家庭地址
    private String address;
    //病史
    private String medicalHistory;
    //自理能力
    private String careOfOneself;
    //护理人名字
    private String caregiversName;
    //（护理人）与老人关系
    private String relationsElder;
    //（护理人）电话
    private String phone;
    //（老人）房号
    private Integer room;
    //（老人）床号
    private Integer bed;
    //头像
    private String portrait;
    //文件
    private String fileId;

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCareOfOneself() {
        return careOfOneself;
    }

    public void setCareOfOneself(String careOfOneself) {
        this.careOfOneself = careOfOneself;
    }

    public String getCaregiversName() {
        return caregiversName;
    }

    public void setCaregiversName(String caregiversName) {
        this.caregiversName = caregiversName;
    }

    public String getRelationsElder() {
        return relationsElder;
    }

    public void setRelationsElder(String relationsElder) {
        this.relationsElder = relationsElder;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "Elder{" +
                "id=" + id +
                ", elderName='" + elderName + '\'' +
                ", gender=" + gender +
                ", nation='" + nation + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", careOfOneself='" + careOfOneself + '\'' +
                ", caregiversName='" + caregiversName + '\'' +
                ", relationsElder='" + relationsElder + '\'' +
                ", phone='" + phone + '\'' +
                ", room=" + room +
                ", bed=" + bed +
                ", portrait='" + portrait + '\'' +
                ", fileId='" + fileId + '\'' +
                '}';
    }
}
