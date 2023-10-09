package uk.co.planetcom.infrastructure.ota.server.controllers.webhooks.github.cosmo_codios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.planetcom.infrastructure.ota.server.controllers.webhooks.github.BaseGitHubWebhookAbstractClass;

import java.util.Map;

@Slf4j
@RestController
// @Hidden
@RequestMapping("/api/v1/webhooks/github")
public class CosmoCoDiOSWebhookController extends BaseGitHubWebhookAbstractClass {

    @Value("${github.webhooks.secrets.codid}")
    private String WEBHOOK_SECRET;

    @Override
    protected void dispatch() {
    }

    @Operation(summary = "Process incoming payloads from Cosmo-CoDiOS GitHub webhooks",
        description = "Accept JSON payloads from GitHub (Cosmo-CoDiOS/codid) for new releases, including nightlies.")
    @ApiResponses(value = { @ApiResponse(responseCode = "202",
        description = "Payload dispatched to event handler."),
        @ApiResponse(responseCode = "401",
            description = "Unauthorized signature. Possible invalid webhook.")})
    @Override
    @PostMapping(value = "/codid", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> receiveWebhook(@RequestHeader("X-Hub-Signature") String sig, @RequestBody String payload) {
        return super.doReceiveWebhook(sig, payload);
    }
}