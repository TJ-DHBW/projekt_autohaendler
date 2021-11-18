package util;

public interface ICryptoLogger {
    void onTransaction(String event, Object argument);
    void onBroadcast(String event, Object argument);
    void onBroadcastVerification(String event, Object argument);
    void onStructuring(String event, Object argument);
    void onProofOfWork(String event, Object argument);
    void onBlockTransmission(String event, Object argument);
    void onTransmissionVerification(String event, Object argument);
    void onBlockAdded(String event, Object argument);
}
