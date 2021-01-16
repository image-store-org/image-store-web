<template>
    <div id="app">
        <loader class="loader pre-loader"
                v-if="isLoading"
        />
        <div v-else
             id="nav">
            <router-link to="/">
                Home
            </router-link> |
            <router-link to="/about">
                About
            </router-link>
        </div>
        <router-view v-if="!isLoading"/>
    </div>
</template>

<style lang="scss">
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }
    #nav {
        padding: 30px;
        a {
            font-weight: bold;
            color: #2c3e50;
            &.router-link-exact-active {
                color: #42b983;
            }
        }
    }
</style>
<script>
    import Loader from "@/components/Loader";
    import {ref} from "vue";

    export default {
        components: {Loader},
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