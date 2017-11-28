package it.unicas.project.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Sensing Element.
 *
 * Uses the DTO pattern.
 */
public final class SensingElement {

    private StringProperty id;
    private IntegerProperty rSense;
    private Integer inGain;
    private Integer outGain;
    private String contacts;
    private Integer frequency;
    private String harmonic;
    private Integer dcBias;
    private String modeVI;
    private String measureTechnique;
    private String measureType;
    private Integer filter;
    private String phaseShiftMode;
    private Integer phaseShift;
    private String iq;
    private Integer conversionRate;
    private String inPortADC;
    private Integer nData;
    private String measureUnit;

    public SensingElement() {
        this.id = new SimpleStringProperty("");
        //this.rSense = 0;
        this.rSense = new SimpleIntegerProperty(0);
        this.inGain = 0;
        this.outGain = 0;
        this.contacts = "";
        this.frequency = 0;
        this.harmonic = "";
        this.dcBias = 0;
        this.modeVI = "";
        this.measureTechnique = "";
        this.measureType = "";
        this.filter = 0;
        this.phaseShiftMode = "";
        this.phaseShift = 0;
        this.iq = "";
        this.conversionRate = 0;
        this.inPortADC = "";
        this.nData = 0;
        this.measureUnit = "";
    }

    public SensingElement(String id) {
        this.id = new SimpleStringProperty(id);
        //this.rSense = 0;
        this.rSense = new SimpleIntegerProperty(0);
        this.inGain = 0;
        this.outGain = 0;
        this.contacts = "";
        this.frequency = 0;
        this.harmonic = "";
        this.dcBias = 0;
        this.modeVI = "";
        this.measureTechnique = "";
        this.measureType = "";
        this.filter = 0;
        this.phaseShiftMode = "";
        this.phaseShift = 0;
        this.iq = "";
        this.conversionRate = 0;
        this.inPortADC = "";
        this.nData = 0;
        this.measureUnit = "";
    }

    public SensingElement(String id, Integer rSense,
                          Integer inGain, Integer outGain,
                          String contacts, Integer frequency,
                          String harmonic, Integer dcBias,
                          String modeVI, String measureTechnique,
                          String measureType, Integer filter,
                          String phaseShiftMode, Integer phaseShift,
                          String iq, Integer conversionRate,
                          String inPortADC, Integer nData,
                          String measureUnit) {
        this.id = new SimpleStringProperty(id);
        //this.rSense = rSense;
        this.rSense = new SimpleIntegerProperty(rSense);
        this.inGain = inGain;
        this.outGain = outGain;
        this.contacts = contacts;
        this.frequency = frequency;
        this.harmonic = harmonic;
        this.dcBias = dcBias;
        this.modeVI = modeVI;
        this.measureTechnique = measureTechnique;
        this.measureType = measureType;
        this.filter = filter;
        this.phaseShiftMode = phaseShiftMode;
        this.phaseShift = phaseShift;
        this.iq = iq;
        this.conversionRate = conversionRate;
        this.inPortADC = inPortADC;
        this.nData = nData;
        this.measureUnit = measureUnit;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    /*public Integer getrSense() {
        return rSense;
    }*/

    /*public void setrSense(Integer rSense) {
        this.rSense = rSense;
    }*/

    public int getrSense() {
        return rSense.get();
    }

    public IntegerProperty rSenseProperty() {
        return rSense;
    }

    public void setrSense(int rSense) {
        this.rSense.set(rSense);
    }

    public Integer getInGain() {
        return inGain;
    }

    public void setInGain(Integer inGain) {
        this.inGain = inGain;
    }

    public Integer getOutGain() {
        return outGain;
    }

    public void setOutGain(Integer outGain) {
        this.outGain = outGain;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getHarmonic() {
        return harmonic;
    }

    public void setHarmonic(String harmonic) {
        this.harmonic = harmonic;
    }

    public Integer getDcBias() {
        return dcBias;
    }

    public void setDcBias(Integer dcBias) {
        this.dcBias = dcBias;
    }

    public String getModeVI() {
        return modeVI;
    }

    public void setModeVI(String modelVI) {
        this.modeVI = modelVI;
    }

    public String getMeasureTechnique() {
        return measureTechnique;
    }

    public void setMeasureTechnique(String measureTechnique) {
        this.measureTechnique = measureTechnique;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public Integer getFilter() {
        return filter;
    }

    public void setFilter(Integer filter) {
        this.filter = filter;
    }

    public String getPhaseShiftMode() {
        return phaseShiftMode;
    }

    public void setPhaseShiftMode(String phaseShiftMode) {
        this.phaseShiftMode = phaseShiftMode;
    }

    public Integer getPhaseShift() {
        return phaseShift;
    }

    public void setPhaseShift(Integer phaseShift) {
        this.phaseShift = phaseShift;
    }

    public String getIq() {
        return iq;
    }

    public void setIq(String iq) {
        this.iq = iq;
    }

    public Integer getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Integer conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getInPortADC() {
        return inPortADC;
    }

    public void setInPortADC(String inPortADC) {
        this.inPortADC = inPortADC;
    }

    public Integer getnData() {
        return nData;
    }

    public void setnData(Integer nData) {
        this.nData = nData;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}
