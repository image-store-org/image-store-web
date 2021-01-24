<template>
    <div class="loader">
        <progress-spinner />
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import ProgressSpinner from 'primevue/progressspinner';

export default defineComponent({
    components: { ProgressSpinner }
});

</script>

<style scoped></style>