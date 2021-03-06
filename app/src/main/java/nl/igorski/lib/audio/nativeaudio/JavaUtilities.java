/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package nl.igorski.lib.audio.nativeaudio;

public class JavaUtilities {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected JavaUtilities(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(JavaUtilities obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        MWEngineCoreJNI.delete_JavaUtilities(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static boolean createSampleFromFile(String aKey, String aWAVFilePath) {
    return MWEngineCoreJNI.JavaUtilities_createSampleFromFile(aKey, aWAVFilePath);
  }

  public static boolean createSampleFromAsset(String aKey, Object assetManager, String assetName) {
    return MWEngineCoreJNI.JavaUtilities_createSampleFromAsset(aKey, assetManager, assetName);
  }

  public static void createSampleFromBuffer(String aKey, int aBufferLength, int aChannelAmount, double[] aBuffer, double[] aOptRightBuffer) {
    MWEngineCoreJNI.JavaUtilities_createSampleFromBuffer(aKey, aBufferLength, aChannelAmount, aBuffer, aOptRightBuffer);
  }

  public static void cacheTable(int tableLength, int waveformType) {
    MWEngineCoreJNI.JavaUtilities_cacheTable__SWIG_0(tableLength, waveformType);
  }

  public static void cacheTable(int tableLength, int waveformType, double[] aBuffer) {
    MWEngineCoreJNI.JavaUtilities_cacheTable__SWIG_1(tableLength, waveformType, aBuffer);
  }

  public static boolean createTableFromFile(int waveformType, String aWAVFilePath) {
    return MWEngineCoreJNI.JavaUtilities_createTableFromFile(waveformType, aWAVFilePath);
  }

  public JavaUtilities() {
    this(MWEngineCoreJNI.new_JavaUtilities(), true);
  }

}
