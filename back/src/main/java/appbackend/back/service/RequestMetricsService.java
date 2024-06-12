package appbackend.back.service;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class RequestMetricsService {

    private final MeterRegistry meterRegistry;
    private final Counter totalRequestsCounter;
    private final Counter successfulRequestsCounter;
    private final Counter failedRequestsCounter;
    private final Timer requestTimer;

    @Autowired
    public RequestMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.totalRequestsCounter = meterRegistry.counter("requests.total");
        this.successfulRequestsCounter = meterRegistry.counter("requests.successful");
        this.failedRequestsCounter = meterRegistry.counter("requests.failed");
        this.requestTimer = meterRegistry.timer("requests.timer");
    }

    public void processRequest(boolean isSuccess) {
        totalRequestsCounter.increment();
        if (isSuccess) {
            successfulRequestsCounter.increment();
        } else {
            failedRequestsCounter.increment();
        }
    }

    public void recordRequestTime(long startTimeMillis) {
        long endTimeMillis = System.currentTimeMillis();
        long elapsedTimeMillis = endTimeMillis - startTimeMillis;
        requestTimer.record(elapsedTimeMillis, TimeUnit.MILLISECONDS);
    }
}

