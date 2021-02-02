<template>
    <article class="file">
        <header>
            <i class="pi pi-file"></i>
            <h1>File</h1>
        </header>
        <card>
            <template #title>
                <h3>{{ regexUtils.getCurrentRouteFinalSubPath($router.currentRoute.value.path) }}</h3>
            </template>
            <template #content>
                <router-view></router-view>
            </template>
        </card>
    </article>
</template>

<script>
    import {defineComponent} from "vue";
    import Card from "primevue/card";
    import RegExUtils from "@/scripts/ts/utils/RegExUtils";

    export default defineComponent({
        components: { Card },
        data() {
            return {
                regexUtils: RegExUtils
            }
        }
    });
</script>

<style lang="scss">
    .file {
        display: flex;
        flex-direction: column;
    }
</style>