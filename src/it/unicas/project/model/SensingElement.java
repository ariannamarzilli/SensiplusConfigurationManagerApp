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

    private String id;
    private Integer rSense;
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
    private String name;
    private Double rangeMin;
    private Double rangeMax;
    private Double defaultAlarmThreshold;
    private Integer multiplier;
    private String measureUnit;

    public SensingElement() {
        this.id = "";
        this.rSense = 0;
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
        this.name = "";
        this.rangeMin = 0.0;
        this.rangeMax = 0.0;
        this.defaultAlarmThreshold = 0.0;
        this.multiplier = 0;
        this.measureUnit = "";
    }

    public SensingElement(String id) {
        this.id = id;
        this.rSense = 0;
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
        this.name = "";
        this.rangeMin = 0.0;
        this.rangeMax = 0.0;
        this.defaultAlarmThreshold = 0.0;
        this.multiplier = 0;
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
                          String inPortADC, Integer nData, String name,
                          Double rangeMin, Double rangeMax,
                          Double defaultAlarmThreshold, Integer multiplier,
                          String measureUnit) {
        this.id = id;
        this.rSense = rSense;
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
        this.name = name;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.defaultAlarmThreshold = defaultAlarmThreshold;
        this.multiplier = multiplier;
        this.measureUnit = measureUnit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getrSense() {
        return rSense;
    }

    public void setrSense(Integer rSense) {
        this.rSense = rSense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(Double rangeMin) {
        this.rangeMin = rangeMin;
    }

    public Double getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(Double rangeMax) {
        this.rangeMax = rangeMax;
    }

    public Double getDefaultAlarmThreshold() {
        return defaultAlarmThreshold;
    }

    public void setDefaultAlarmThreshold(Double defaultAlarmThreshold) {
        this.defaultAlarmThreshold = defaultAlarmThreshold;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
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
