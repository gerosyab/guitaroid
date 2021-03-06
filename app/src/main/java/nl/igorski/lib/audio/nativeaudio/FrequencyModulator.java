/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package nl.igorski.lib.audio.nativeaudio;

public class FrequencyModulator extends BaseProcessor {
  private transient long swigCPtr;

  protected FrequencyModulator(long cPtr, boolean cMemoryOwn) {
    super(MWEngineCoreJNI.FrequencyModulator_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(FrequencyModulator obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        MWEngineCoreJNI.delete_FrequencyModulator(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public FrequencyModulator(int aWaveForm, float aRate) {
    this(MWEngineCoreJNI.new_FrequencyModulator(aWaveForm, aRate), true);
  }

  public void process(SWIGTYPE_p_AudioBuffer sampleBuffer, boolean isMonosource) {
    MWEngineCoreJNI.FrequencyModulator_process(swigCPtr, this, SWIGTYPE_p_AudioBuffer.getCPtr(sampleBuffer), isMonosource);
  }

  public float getRate() {
    return MWEngineCoreJNI.FrequencyModulator_getRate(swigCPtr, this);
  }

  public void setRate(float value) {
    MWEngineCoreJNI.FrequencyModulator_setRate(swigCPtr, this, value);
  }

  public int getWave() {
    return MWEngineCoreJNI.FrequencyModulator_getWave(swigCPtr, this);
  }

  public void setWave(int value) {
    MWEngineCoreJNI.FrequencyModulator_setWave(swigCPtr, this, value);
  }

}
