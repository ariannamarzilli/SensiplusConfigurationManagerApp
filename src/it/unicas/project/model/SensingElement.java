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
    private String rSense;
    private String inGain;
    private String outGain;
    private String contacts;
    private Double frequency;
    private String harmonic;
    private Integer dcBias;
    private String modeVI;
    private String measureTechnique;
    private String measureType;
    private Integer filter;
    private String phaseShiftMode;
    private Double phaseShift;
    private String iq;
    private Double conversionRate;
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
        this.rSense = "50";
        this.inGain = "1";
        this.outGain = "0";
        this.contacts = "TWO";
        this.frequency = 78125.0;
        this.harmonic = "FIRST_HARMONIC";
        this.dcBias = 0;
        this.modeVI = "VOUT_IIN";
        this.measureTechnique = "EIS";
        this.measureType = "IN-PHASE";
        this.filter = 1;
        this.phaseShiftMode = "QUADRANT";
        this.phaseShift = 0.0;
        this.iq = "IN_PHASE";
        this.conversionRate = 50.0;
        this.inPortADC = "IA";
        this.nData = 1;
        this.name = "";
        this.rangeMin = 0.0;
        this.rangeMax = 100.0;
        this.defaultAlarmThreshold = 50.0;
        this.multiplier = 0;
        this.measureUnit = "O";
    }

    public SensingElement(String id) {
        this.id = id;
        this.rSense = "50";
        this.inGain = "1";
        this.outGain = "0";
        this.contacts = "TWO";
        this.frequency = 78125.0;
        this.harmonic = "FIRST_HARMONIC";
        this.dcBias = 0;
        this.modeVI = "VOUT_IIN";
        this.measureTechnique = "EIS";
        this.measureType = "IN-PHASE";
        this.filter = 1;
        this.phaseShiftMode = "QUADRANT";
        this.phaseShift = 0.0;
        this.iq = "IN_PHASE";
        this.conversionRate = 50.0;
        this.inPortADC = "IA";
        this.nData = 1;
        this.name = "";
        this.rangeMin = 0.0;
        this.rangeMax = 100.0;
        this.defaultAlarmThreshold = 50.0;
        this.multiplier = 0;
        this.measureUnit = "O";
    }

    public SensingElement(String id, String rSense,
                          String inGain, String outGain,
                          String contacts, Double frequency,
                          String harmonic, Integer dcBias,
                          String modeVI, String measureTechnique,
                          String measureType, Integer filter,
                          String phaseShiftMode, Double phaseShift,
                          String iq, Double conversionRate,
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

    public SensingElement(SensingElement sensingElement) {
        this.id = sensingElement.getId();
        this.rSense = sensingElement.getrSense();
        this.inGain = sensingElement.getInGain();
        this.outGain = sensingElement.getOutGain();
        this.contacts = sensingElement.getContacts();
        this.frequency = sensingElement.getFrequency();
        this.harmonic = sensingElement.getHarmonic();
        this.dcBias = sensingElement.getDcBias();
        this.modeVI = sensingElement.getModeVI();
        this.measureTechnique = sensingElement.getMeasureTechnique();
        this.measureType = sensingElement.getMeasureType();
        this.filter = sensingElement.getFilter();
        this.phaseShiftMode = sensingElement.getPhaseShiftMode();
        this.phaseShift = sensingElement.getPhaseShift();
        this.iq = sensingElement.getIq();
        this.conversionRate = sensingElement.getConversionRate();
        this.inPortADC = sensingElement.getInPortADC();
        this.nData = sensingElement.getnData();
        this.name = sensingElement.getName();
        this.rangeMin = sensingElement.getRangeMin();
        this.rangeMax = sensingElement.getRangeMax();
        this.defaultAlarmThreshold = sensingElement.getDefaultAlarmThreshold();
        this.multiplier = sensingElement.getMultiplier();
        this.measureUnit = sensingElement.getMeasureUnit();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getrSense() {
        return rSense;
    }

    public void setrSense(String rSense) {
        this.rSense = rSense;
    }

    public String getInGain() {
        return inGain;
    }

    public void setInGain(String inGain) {
        this.inGain = inGain;
    }

    public String getOutGain() {
        return outGain;
    }

    public void setOutGain(String outGain) {
        this.outGain = outGain;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
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

    public void setModeVI(String modeVI) {
        this.modeVI = modeVI;
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

    public Double getPhaseShift() {
        return phaseShift;
    }

    public void setPhaseShift(Double phaseShift) {
        this.phaseShift = phaseShift;
    }

    public String getIq() {
        return iq;
    }

    public void setIq(String iq) {
        this.iq = iq;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
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

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public boolean equals(SensingElement sensingElement) {
        if (this.getId().equals(sensingElement.getId()) &&
                this.getName().equals(sensingElement.getName()) &&
                this.getrSense().equals(sensingElement.getrSense()) &&
                this.getInGain().equals(sensingElement.getInGain()) &&
                this.getOutGain().equals(sensingElement.getOutGain()) &&
                this.getContacts().equals(sensingElement.getContacts()) &&
                this.getFrequency().equals(sensingElement.getFrequency()) &&
                this.getHarmonic().equals(sensingElement.getHarmonic()) &&
                this.getDcBias().equals(sensingElement.getDcBias()) &&
                this.getModeVI().equals(sensingElement.getModeVI()) &&
                this.getMeasureTechnique().equals(sensingElement.getMeasureTechnique()) &&
                this.getMeasureType().equals(sensingElement.getMeasureType()) &&
                this.getFilter().equals(sensingElement.getFilter()) &&
                this.getPhaseShiftMode().equals(sensingElement.getPhaseShiftMode()) &&
                this.getPhaseShift().equals(sensingElement.getPhaseShift()) &&
                this.getIq().equals(sensingElement.getIq()) &&
                this.getConversionRate().equals(sensingElement.getConversionRate()) &&
                this.getInPortADC().equals(sensingElement.getInPortADC()) &&
                this.getnData().equals(sensingElement.getnData()) &&
                this.getRangeMin().equals(sensingElement.getRangeMin()) &&
                this.getRangeMax().equals(sensingElement.getRangeMax()) &&
                this.getDefaultAlarmThreshold().equals(sensingElement.getDefaultAlarmThreshold()) &&
                this.getMultiplier().equals(sensingElement.getMultiplier()) &&
                this.getMeasureUnit().equals(sensingElement.getMeasureUnit())) {
            return true;
        }
        return false;
    }

    public void checkNullField() {
        if (this.getName() == null) {
            this.setName("");
        }
        if (this.getrSense() == null) {
            this.setrSense("");
        }
        if (this.getInGain() == null) {
            this.setInGain("");
        }
        if (this.getOutGain() == null) {
            this.setOutGain("");
        }
        if (this.getContacts() == null) {
            this.setContacts("");
        }
        if (this.getFrequency() == null) {
            this.setFrequency(Double.MAX_VALUE);
        }
        if (this.getHarmonic() == null) {
            this.setHarmonic("");
        }
        if (this.getDcBias() == null) {
            this.setDcBias(Integer.MAX_VALUE);
        }
        if (this.getModeVI() == null) {
            this.setModeVI("");
        }
        if (this.getMeasureTechnique() == null) {
            this.setMeasureTechnique("");
        }
        if (this.getMeasureType() == null) {
            this.setMeasureType("");
        }
        if (this.getFilter() == null) {
            this.setFilter(Integer.MAX_VALUE);
        }
        if (this.getPhaseShiftMode() == null) {
            this.setPhaseShiftMode("");
        }
        if (this.getPhaseShift() == null) {
            this.setPhaseShift(Double.MAX_VALUE);
        }
        if (this.getIq() == null) {
            this.setIq("");
        }
        if (this.getConversionRate() == null) {
            this.setConversionRate(Double.MAX_VALUE);
        }
        if (this.getInPortADC() == null) {
            this.setInPortADC("");
        }
        if (this.getnData() == null) {
            this.setnData(Integer.MAX_VALUE);
        }
        if (this.getRangeMin() == null) {
            this.setRangeMin(Double.MAX_VALUE);
        }
        if (this.getRangeMax() == null) {
            this.setRangeMax(Double.MAX_VALUE);
        }
        if (this.getDefaultAlarmThreshold() == null) {
            this.setDefaultAlarmThreshold(Double.MAX_VALUE);
        }
        if (this.getMultiplier() == null) {
            this.setMultiplier(Integer.MAX_VALUE);
        }
        if (this.getMeasureUnit() == null) {
            this.setMeasureUnit("");
        }
    }
}