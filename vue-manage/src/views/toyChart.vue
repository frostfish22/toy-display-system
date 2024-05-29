<template>
    <div class="container">
        <div class="context">
            <div id="pie"><pieChart/></div>
            <div id="bar"><barChart/></div>
        </div>
        <div class="context">
            <div id="line"><lineChart/></div>
            <div id="radar"><radarChart/></div>
        </div>
    </div>
</template>

<script setup>
import pieChart from '@/components/charts/pieChart.vue'
import barChart from '@/components/charts/barChart.vue'
import lineChart from '@/components/charts/lineChart.vue'
import radarChart from '@/components/charts/radarChart.vue'
</script>

<style scoped>
.context{
    display: flex;
    justify-content: space-between;
    height: 100%;
}
#pie,#bar,#line,#radar{
    width: 50%;
    height: 400px;
    float: left;
    margin: 1px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 0 10px #ccc;
    background-color: #fff;
    padding: 10px;
    overflow: hidden;
}
</style>