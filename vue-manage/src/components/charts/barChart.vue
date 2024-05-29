<template>
    <div>
        <v-chart class="chart" :option="option" autoresize />
    </div>
</template>

<script setup name="pieChart">
import VChart, { THEME_KEY } from 'vue-echarts';
import { ref, provide } from 'vue';

import { CanvasRenderer } from 'echarts/renderers'
import { use, connect } from 'echarts/core'
import { ScatterChart, BarChart, CandlestickChart } from 'echarts/charts'
import {
    MarkPointComponent,
    DataZoomComponent,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
} from 'echarts/components'
use([
    DataZoomComponent,
    MarkPointComponent,
    BarChart,
    ScatterChart,
    CanvasRenderer,
    CandlestickChart,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
])



provide(THEME_KEY, 'white');

const option = ref({
    xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
        }
    ]
});
</script>

<style scoped>
.chart {
    height: 400px;
}
</style>