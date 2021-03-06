/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package nl.igorski.lib.audio.mwengine;

public class Finalizer extends Limiter {
  private transient long swigCPtr;

  protected Finalizer(long cPtr, boolean cMemoryOwn) {
    super(MWEngineCoreJNI.Finalizer_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Finalizer obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        MWEngineCoreJNI.delete_Finalizer(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public Finalizer(float attackMs, float releaseMs, int sampleRate, int amountOfChannels) {
    this(MWEngineCoreJNI.new_Finalizer(attackMs, releaseMs, sampleRate, amountOfChannels), true);
  }

  public void process(SWIGTYPE_p_AudioBuffer sampleBuffer, boolean isMonoSource) {
    MWEngineCoreJNI.Finalizer_process(swigCPtr, this, SWIGTYPE_p_AudioBuffer.getCPtr(sampleBuffer), isMonoSource);
  }

}
