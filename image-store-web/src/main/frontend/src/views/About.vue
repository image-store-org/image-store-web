<template>
    <div class="about">
        <h1>This is an about page</h1>
        <button @click="increase">Clicked {{ count }} times.</button>
    </div>
</template>

<script lang="ts">
    import {defineComponent, ref} from "vue";

    export default defineComponent({
        setup() {
            const count = ref(0)
            const increase = () => {
                count.value++
            }

            return {
                count,
                increase,
            }
        }
    });
</script>

<style scoped lang="scss">
    h1 {
        font-size: 50px;
    }
</style>
