package appbackend.back.config;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfiguration {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> commonTags() {
        return registry -> registry.config().commonTags("application", "appbackend");
    }

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> configureRegistry() {
        return registry -> {
            registry.config()
                    .meterFilter(new MeterFilter() {
                        @Override
                        public MeterFilterReply accept(Meter.Id id) {
                            // Ví dụ: Bỏ qua tất cả các meters có tag `region` là `test`
                            String region = id.getTag("region");
                            if ("test".equals(region)) {
                                return MeterFilterReply.DENY;
                            }
                            return MeterFilterReply.NEUTRAL;
                        }
                    });
        };
    }
}
