/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package nl.igorski.lib.audio.mwengine;

public class LPFHPFilter extends BaseProcessor {
  private transient long swigCPtr;

  protected LPFHPFilter(long cPtr, boolean cMemoryOwn) {
    super(MWEngineCoreJNI.LPFHPFilter_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(LPFHPFilter obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        MWEngineCoreJNI.delete_LPFHPFilter(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public LPFHPFilter(float aLPCutoff, float aHPCutoff, int amountOfChannels) {
    this(MWEngineCoreJNI.new_LPFHPFilter(aLPCutoff, aHPCutoff, amountOfChannels), true);
  }

  public void setLPF(float aCutOffFrequency, int aSampleRate) {
    MWEngineCoreJNI.LPFHPFilter_setLPF(swigCPtr, this, aCutOffFrequency, aSampleRate);
  }

  public void setHPF(float aCutOffFrequency, int aSampleRate) {
    MWEngineCoreJNI.LPFHPFilter_setHPF(swigCPtr, this, aCutOffFrequency, aSampleRate);
  }

  public void process(SWIGTYPE_p_AudioBuffer sampleBuffer, boolean isMonoSource) {
    MWEngineCoreJNI.LPFHPFilter_process(swigCPtr, this, SWIGTYPE_p_AudioBuffer.getCPtr(sampleBuffer), isMonoSource);
  }

}