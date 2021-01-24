<template>
    <div class="loader pre-loader">
        <progress-spinner />
    </div>
</template>

<script type="application/javascript">
import { defineComponent } from "vue";
import ProgressSpinner from 'primevue/progressspinner';

export default defineComponent({
    components: { ProgressSpinner }
});

</script>

<style type="text/css" scoped>
.loader.pre-loader {
    position: absolute;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
</style>