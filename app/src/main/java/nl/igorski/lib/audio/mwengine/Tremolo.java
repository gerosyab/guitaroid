/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package nl.igorski.lib.audio.mwengine;

public class Tremolo extends BaseProcessor {
  private transient long swigCPtr;

  protected Tremolo(long cPtr, boolean cMemoryOwn) {
    super(MWEngineCoreJNI.Tremolo_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Tremolo obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        MWEngineCoreJNI.delete_Tremolo(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public Tremolo(int aLeftType, int aLeftAttack, int aLeftDecay, int aRightType, int aRightAttack, int aRightDecay) {
    this(MWEngineCoreJNI.new_Tremolo(aLeftType, aLeftAttack, aLeftDecay, aRightType, aRightAttack, aRightDecay), true);
  }

  public int getLeftAttack() {
    return MWEngineCoreJNI.Tremolo_getLeftAttack(swigCPtr, this);
  }

  public void setLeftAttack(int aAttack) {
    MWEngineCoreJNI.Tremolo_setLeftAttack(swigCPtr, this, aAttack);
  }

  public int getRightAttack() {
    return MWEngineCoreJNI.Tremolo_getRightAttack(swigCPtr, this);
  }

  public void setRightAttack(int aAttack) {
    MWEngineCoreJNI.Tremolo_setRightAttack(swigCPtr, this, aAttack);
  }

  public int getLeftDecay() {
    return MWEngineCoreJNI.Tremolo_getLeftDecay(swigCPtr, this);
  }

  public void setLeftDecay(int aDecay) {
    MWEngineCoreJNI.Tremolo_setLeftDecay(swigCPtr, this, aDecay);
  }

  public int getRightDecay() {
    return MWEngineCoreJNI.Tremolo_getRightDecay(swigCPtr, this);
  }

  public void setRightDecay(int aDecay) {
    MWEngineCoreJNI.Tremolo_setRightDecay(swigCPtr, this, aDecay);
  }

  public SWIGTYPE_p_SAMPLE_TYPE getTableForChannel(int aChannelNum) {
    long cPtr = MWEngineCoreJNI.Tremolo_getTableForChannel(swigCPtr, this, aChannelNum);
    return (cPtr == 0) ? null : new SWIGTYPE_p_SAMPLE_TYPE(cPtr, false);
  }

  public boolean isStereo() {
    return MWEngineCoreJNI.Tremolo_isStereo(swigCPtr, this);
  }

  public void process(SWIGTYPE_p_AudioBuffer sampleBuffer, boolean isMonoSource) {
    MWEngineCoreJNI.Tremolo_process(swigCPtr, this, SWIGTYPE_p_AudioBuffer.getCPtr(sampleBuffer), isMonoSource);
  }

  public enum types {
    LINEAR,
    EXPONENTIAL;

    public final int swigValue() {
      return swigValue;
    }

    public static types swigToEnum(int swigValue) {
      types[] swigValues = types.class.getEnumConstants();
      if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
        return swigValues[swigValue];
      for (types swigEnum : swigValues)
        if (swigEnum.swigValue == swigValue)
          return swigEnum;
      throw new IllegalArgumentException("No enum " + types.class + " with value " + swigValue);
    }

    @SuppressWarnings("unused")
    private types() {
      this.swigValue = SwigNext.next++;
    }

    @SuppressWarnings("unused")
    private types(int swigValue) {
      this.swigValue = swigValue;
      SwigNext.next = swigValue+1;
    }

    @SuppressWarnings("unused")
    private types(types swigEnum) {
      this.swigValue = swigEnum.swigValue;
      SwigNext.next = this.swigValue+1;
    }

    private final int swigValue;

    private static class SwigNext {
      private static int next = 0;
    }
  }

  public final static int ENVELOPE_PRECISION = MWEngineCoreJNI.Tremolo_ENVELOPE_PRECISION_get();
}
