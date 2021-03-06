package org.elasticsearch.plugin.prometheus;

import org.apache.logging.log4j.Logger;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.index.IndexModule;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.rest.prometheus.RestPrometheusMetricsAction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class PrometheusExporterPlugin extends Plugin implements ActionPlugin {

    private final Logger logger = Loggers.getLogger(PrometheusExporterPlugin.class);

    @Inject
    public PrometheusExporterPlugin(Settings settings) {
        logger.info("starting Prometheus exporter plugin...");
    }

    @Override
    public void onIndexModule(IndexModule indexModule) {
        super.onIndexModule(indexModule);
    }

    @Override
    public List<RestHandler> getRestHandlers(Settings settings, RestController restController, ClusterSettings clusterSettings, IndexScopedSettings indexScopedSettings, SettingsFilter settingsFilter, IndexNameExpressionResolver indexNameExpressionResolver, Supplier<DiscoveryNodes> nodesInCluster) {
        return Arrays.asList(
                new RestPrometheusMetricsAction(settings, restController)
        );
    }

}
