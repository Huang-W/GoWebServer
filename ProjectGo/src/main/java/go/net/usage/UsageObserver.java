package go.net.usage;

/**
 * Observers who are interested in when the usage of the application changes.
 */
public interface UsageObserver {
    /**
     * An event when the usage statistics of the application change.
     */
    void usageUpdated();
}
