/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package nl.igorski.lib.audio.mwengine;

public class SynthEvent extends BaseSynthEvent {
  private transient long swigCPtr;

  protected SynthEvent(long cPtr, boolean cMemoryOwn) {
    super(MWEngineCoreJNI.SynthEvent_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SynthEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        MWEngineCoreJNI.delete_SynthEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public SynthEvent(float aFrequency, int aPosition, float aLength, SynthInstrument aInstrument) {
    this(MWEngineCoreJNI.new_SynthEvent__SWIG_0(aFrequency, aPosition, aLength, SynthInstrument.getCPtr(aInstrument), aInstrument), true);
  }

  public SynthEvent(float aFrequency, SynthInstrument aInstrument) {
    this(MWEngineCoreJNI.new_SynthEvent__SWIG_1(aFrequency, SynthInstrument.getCPtr(aInstrument), aInstrument), true);
  }

}