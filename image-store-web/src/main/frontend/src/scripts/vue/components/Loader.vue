<template>
    <div class="loader pre-loader">
        <progress-spinner />
    </div>
</template>

<script>
import { defineComponent } from "vue";
import ProgressSpinner from 'primevue/progressspinner';

export default defineComponent({
    components: { ProgressSpinner }
});

</script>

<style scoped>
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