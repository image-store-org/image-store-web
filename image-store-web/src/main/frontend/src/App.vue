<template>
    <div id="app">
        <loader class="loader pre-loader"
                v-if="isLoading"
        />
        <div v-else
             id="nav">
            <navbar />
            <router-link to="/">
                Home
            </router-link> |
            <router-link to="/about">
                About
            </router-link>
        </div>
        <router-view v-if="!isLoading" />
    </div>
</template>

<script>
    import Loader from "@/components/Loader";
    import {ref} from "vue";
    import Navbar from "@/components/Navbar";

    export default {
        components: {Navbar, Loader },
        setup() {
            const isLoading = ref(true);
            document.onreadystatechange = () => {
                setTimeout(() => {
                    isLoading.value = document.readyState !== "complete";
                }, 1000);
            }
            return {
                isLoading
            }
        }
    }
</script>

<style scoped lang="scss">
    .loader.pre-loader{
        position: absolute;
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
</style>