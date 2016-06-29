package com.bms.system.bean;

/**
 * @author : zhaoyi
 * @version : 1.0.0
 * @date : 2016/3/30 14:20
 * @description : excel导入的列名bean
 */
public class ExcelColumnBean
{
    private String id;
    private String userId;
    private String geneNo;
    private String geneName;
    private String ncbiAccount;
    private String usageDescribtion;
    private String carrier;
    private String fragmentLen;
    private String resistanceScreening;
    private String geneticRoot;
    private String plasmidEnzyme;
    private String rnaEnzyme;
    private String citingPaper;
    private String lab;
    private String preserverName;
    private String affiliatedUnit;
    private String address;
    private String eMail;
    private String plasmidProfile;
    private String insertSequence;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getGeneNo()
    {
        return geneNo;
    }

    public void setGeneNo(String geneNo)
    {
        this.geneNo = geneNo;
    }

    public String getGeneName()
    {
        return geneName;
    }

    public void setGeneName(String geneName)
    {
        this.geneName = geneName;
    }

    public String getNcbiAccount()
    {
        return ncbiAccount;
    }

    public void setNcbiAccount(String ncbiAccount)
    {
        this.ncbiAccount = ncbiAccount;
    }

    public String getUsageDescribtion()
    {
        return usageDescribtion;
    }

    public void setUsageDescribtion(String usageDescribtion)
    {
        this.usageDescribtion = usageDescribtion;
    }

    public String getCarrier()
    {
        return carrier;
    }

    public void setCarrier(String carrier)
    {
        this.carrier = carrier;
    }

    public String getFragmentLen()
    {
        return fragmentLen;
    }

    public void setFragmentLen(String fragmentLen)
    {
        this.fragmentLen = fragmentLen;
    }

    public String getResistanceScreening()
    {
        return resistanceScreening;
    }

    public void setResistanceScreening(String resistanceScreening)
    {
        this.resistanceScreening = resistanceScreening;
    }

    public String getGeneticRoot()
    {
        return geneticRoot;
    }

    public void setGeneticRoot(String geneticRoot)
    {
        this.geneticRoot = geneticRoot;
    }

    public String getPlasmidEnzyme()
    {
        return plasmidEnzyme;
    }

    public void setPlasmidEnzyme(String plasmidEnzyme)
    {
        this.plasmidEnzyme = plasmidEnzyme;
    }

    public String getRnaEnzyme()
    {
        return rnaEnzyme;
    }

    public void setRnaEnzyme(String rnaEnzyme)
    {
        this.rnaEnzyme = rnaEnzyme;
    }

    public String getCitingPaper()
    {
        return citingPaper;
    }

    public void setCitingPaper(String citingPaper)
    {
        this.citingPaper = citingPaper;
    }

    public String getLab()
    {
        return lab;
    }

    public void setLab(String lab)
    {
        this.lab = lab;
    }

    public String getPreserverName()
    {
        return preserverName;
    }

    public void setPreserverName(String preserverName)
    {
        this.preserverName = preserverName;
    }

    public String getAffiliatedUnit()
    {
        return affiliatedUnit;
    }

    public void setAffiliatedUnit(String affiliatedUnit)
    {
        this.affiliatedUnit = affiliatedUnit;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String geteMail()
    {
        return eMail;
    }

    public void seteMail(String eMail)
    {
        this.eMail = eMail;
    }

    public String getPlasmidProfile()
    {
        return plasmidProfile;
    }

    public void setPlasmidProfile(String plasmidProfile)
    {
        this.plasmidProfile = plasmidProfile;
    }

    public String getInsertSequence()
    {
        return insertSequence;
    }

    public void setInsertSequence(String insertSequence)
    {
        this.insertSequence = insertSequence;
    }

    @Override
    public String toString()
    {
        return "ExcelColumnBean{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", geneNo='" + geneNo + '\'' +
                ", geneName='" + geneName + '\'' +
                ", ncbiAccount='" + ncbiAccount + '\'' +
                ", usageDescribtion='" + usageDescribtion + '\'' +
                ", carrier='" + carrier + '\'' +
                ", fragmentLen='" + fragmentLen + '\'' +
                ", resistanceScreening='" + resistanceScreening + '\'' +
                ", geneticRoot='" + geneticRoot + '\'' +
                ", plasmidEnzyme='" + plasmidEnzyme + '\'' +
                ", rnaEnzyme='" + rnaEnzyme + '\'' +
                ", citingPaper='" + citingPaper + '\'' +
                ", lab='" + lab + '\'' +
                ", preserverName='" + preserverName + '\'' +
                ", affiliatedUnit='" + affiliatedUnit + '\'' +
                ", address='" + address + '\'' +
                ", eMail='" + eMail + '\'' +
                ", plasmidProfile='" + plasmidProfile + '\'' +
                ", insertSequence='" + insertSequence + '\'' +
                '}';
    }
}
