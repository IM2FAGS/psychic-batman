package abey.entities;

/**
 *
 * @author Anthony
 */
public enum ModePaiement {

    CB("CB"),
    CHEQUE("Check"),
    PAYPAL("Paypal");

    private final String bundleKey;

    private ModePaiement(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String getBundleKey() {
        return bundleKey;
    }
}
