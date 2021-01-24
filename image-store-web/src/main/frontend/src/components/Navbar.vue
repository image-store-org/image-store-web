<template>
    <menubar :model="items">
        <template #start>
            <img alt="logo" src="../assets/logo.png" height="40" class="p-mr-2">
        </template>
    </menubar>
</template>

<script lang="ts">
    import {defineComponent} from "vue";
    import Menubar from "primevue/menubar";

    export default defineComponent({
        components: { Menubar },
        data() {
            return {
                items: [
                    {
                        label:'File',
                        icon:'pi pi-fw pi-file',
                        items: [
                            {
                                label:'Upload',
                                icon:'pi pi-fw pi-plus'
                            },
                            {
                                label:'Browse',
                                icon:'pi pi-fw pi-search'
                            }
                        ]
                    },
                    {
                        label:'User',
                        icon:'pi pi-fw pi-user',
                        items:[
                            {
                                label:'Edit',
                                icon:'pi pi-fw pi-user-edit',

                            },
                            {
                                separator: true
                            },
                            {
                                label:'Logout',
                                icon:'pi pi-fw pi-power-off'
                            }
                        ]
                    },
                ]
            }
        }
    });
</script>

<style lang="scss">
    .p-menubar {
        justify-content: space-between;
    }
    .p-menubar .p-menubar-root-list ul {
        right: 0;
        left: auto;
    }
    .p-menubar .p-menubar-root-list .p-menuitem .p-submenu-list .p-menuitem-icon.pi-power-off {
        color: red;
    }
</style>