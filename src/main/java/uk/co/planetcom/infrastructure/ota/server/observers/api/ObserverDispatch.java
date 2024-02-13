package uk.co.planetcom.infrastructure.ota.server.observers.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import uk.co.planetcom.infrastructure.ota.server.controllers.webhooks.github.cosmo_codios.CosmoCoDiOSWebhookController;
import uk.co.planetcom.infrastructure.ota.server.domain.Asset;
import uk.co.planetcom.infrastructure.ota.server.observers.events.AssetDeviceNotifyEvent;
import uk.co.planetcom.infrastructure.ota.server.observers.events.BaseEvent;
import uk.co.planetcom.infrastructure.ota.server.observers.events.GitHubEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class ObserverDispatch implements ApplicationListener<BaseEvent> {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public void onApplicationEvent(final BaseEvent evt) {
        executorService.submit(() -> {
            log.debug("Dispatching event: {}", evt.toString());
            if (evt instanceof GitHubEvent) {
                handleGitHubEvent((GitHubEvent) evt);
            }
        });
    }

    private synchronized void handleGitHubEvent(final GitHubEvent evt) {
        log.info("Handling GitHub event: {}", evt.toString());
        // Update Asset DB.
    }

    private synchronized void handleAssetDeviceNotifyEvent(final AssetDeviceNotifyEvent evt) {
        log.info("Handling AssetDeviceNotify event: {}", evt.toString());
        // Notify devices.
        Asset asset = evt.getAsset();
    }
}