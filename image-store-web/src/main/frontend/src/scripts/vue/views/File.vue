<template>
    <article class="file">
        <header>
            <i class="pi pi-file"></i>
            <h1>File</h1>
        </header>
        <router-view></router-view>
    </article>
</template>

<script>
    import {defineComponent} from "vue";

    export default defineComponent({
    });
</script>

<style lang="scss">
    .file {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
</style>