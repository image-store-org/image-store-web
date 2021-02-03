<template>
    <div class="upload">
        <div class="file-input-container"
             :class="{ 'file-input--file-hover': isFileHoverContainer }"
             ref="fileInputContainerElement"
             @drop.prevent="OnUploadDragDrop"
             @dragover.prevent="OnUploadDragEnter"
             @dragenter.prevent="OnUploadDragEnter"
             @dragleave="OnUploadDragLeave">
            <input class="file-input"
                   type="file"
                   required
                   multiple
                   :accept="fileExtensionsList"
                   ref="fileInputElement"
                   @change.prevent="onFileInputChange"/>
            <i class="file-input__icon pi pi-download" />
            <Button class="p-button-secondary"
                    label="Choose"
                    icon="pi pi-images"
                    iconPos="right"
                    @click.prevent="$refs.fileInputElement.click()" />
        </div>
        <file-table v-show="fileList.length"
                    :file-list="fileList"
                    @deleteFile="deleteFile" />
    </div>
</template>

<script lang="ts">
    import { defineComponent, ref } from "vue";
    import { useToast } from "primevue/usetoast";
    import Button from "primevue/button";
    import FileUtils from "@/scripts/ts/utils/FileUtils";
    import FileTable from "@/scripts/vue/components/FileTable.vue";
    import RegExUtils from "@/scripts/ts/utils/RegExUtils";

    export default defineComponent({
        name: "upload",
        components: { FileTable, Button },
        setup() {
            const toast = useToast();
            const fileList = ref<File[]>([]);
            const fileInputContainerElement = ref<HTMLElement | null>(null);
            const fileInputElement = ref<HTMLInputElement | null>(null);
            const previewListElement = ref<HTMLElement | null>(null);
            const filesTotalSizeBytes = ref<number>(0);
            const filesMaxSizeBytes: number = 3495253;
            const isFileHoverContainer = ref<boolean>(false);
            const fileExtensionsList: string[] = [".jpg"];
            return {
                toast,
                fileList,
                fileInputContainerElement,
                fileInputElement,
                previewListElement,
                filesTotalSizeBytes,
                filesMaxSizeBytes,
                isFileHoverContainer,
                fileExtensionsList
            }
        },
        methods: {
            OnUploadDragEnter(): void {
                if (!this.isFileHoverContainer) {
                    this.isFileHoverContainer = true;
                }
            },
            OnUploadDragLeave(): void {
                if (this.isFileHoverContainer) {
                    this.isFileHoverContainer = false;
                }
            },
            OnUploadDragDrop(event: any): void {
                this.onFileInputChange(event);
                this.isFileHoverContainer = false;
            },
            onFileInputChange(event: any): void {
                let files: FileList = event.target.files || event.dataTransfer.files;
                this._addFiles(files);
                this._clearFileUploadElement();
            },
            _addFile(file: File): void {
                this.fileList.push(file);
                this.filesTotalSizeBytes += file.size;
            },
            _addFiles(files: FileList): void {
                let fileNames: string[] = [];
                for (let i = 0; i < files.length; i++) {
                    const file: File = files[i];
                    if (this._isFileValid(file)) {
                        this._addFile(file);
                        fileNames.push(file.name);
                    } else {
                        return;
                    }
                }
                this._successAddFiles(fileNames);
            },
            deleteFile(index: number, file: File): boolean {
                if (index >= 0) {
                    const fileSize: number = this.fileList[index].size;
                    if (this.fileList.splice(index, 1).length > 0) {
                        this.filesTotalSizeBytes = this.filesTotalSizeBytes - fileSize;
                        this._successDeleteFile(file.name)
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            },
            _deleteFiles(): void {
                this.fileList = [];
            },
            _isFileValid(file: File): boolean {
                return this._isFileExtensionValid(file) &&
                    this._isFileDuplicationValid(file) &&
                    this._isFileSizeValid(file) &&
                    this._isFilesTotalSizeValid();
            },
            _isFileExtensionValid(file: File): boolean {
                const ext: string = file.name.match(RegExUtils.FILE_EXTENSION)![0];
                const extToLower: string = ext.toLowerCase();
                let isValid: boolean = this.fileExtensionsList
                    .some((allowedExt: string) => extToLower === allowedExt.toLowerCase());
                if(!isValid) {
                    this._errorFileExtension(file);
                }
                return isValid;
            },
            _isFileDuplicationValid(file: File): boolean {
                for (let i = 0; i < this.fileList.length; i++) {
                    if (FileUtils.isEqual(this.fileList[i], file)) {
                        this._errorFileDuplicate(file);
                        return false;
                    }
                }
                return true;
            },
            _isFileSizeValid(file: File): boolean {
                let filesTotalSizeBytes: number = this.filesTotalSizeBytes + file.size;
                if (filesTotalSizeBytes > this.filesMaxSizeBytes) {
                    this._errorFileSize(filesTotalSizeBytes, this.filesMaxSizeBytes);
                    return false;
                }
                return true;
            },
            _isFilesTotalSizeValid(): boolean {
                if (this.filesTotalSizeBytes > this.filesMaxSizeBytes) {
                    this._errorFileSize(this.filesTotalSizeBytes, this.filesMaxSizeBytes);
                    return false;
                }
                return true;
            },
            _clearFileUploadElement(): void {
                if (this.fileInputElement) {
                    this.fileInputElement.value = "";
                }
            },
            _successAddFiles(fileNames: string[]): void {
                let detail: string = fileNames.length > 1 ? fileNames.length + " files" : fileNames.toString();
                let detailTrail: string = " added";
                this.toast.add({
                    severity: "success",
                    summary: "Success!",
                    detail: detail + detailTrail,
                    life: 10000
                });
            },
            _successDeleteFile(fileName: string): void {
                let detail: string = fileName + " deleted";
                this.toast.add({
                    severity: "success",
                    summary: "Success!",
                    detail: detail,
                    life: 10000
                });
            },
            _errorFileExtension(file: File): void {
                this.toast.add({
                    severity: "error",
                    summary: "Invalid file extension",
                    detail:`${file.name} has an unsupported file extension. Supported extensions: ${this.fileExtensionsList}`,
                    life: 10000
                });
            },
            _errorFileDuplicate(file: File): void {
                this.toast.add({
                    severity: "error",
                    summary: "Duplicate file",
                    detail:`${file.name} is a duplicate`,
                    life: 10000
                });
            },
            _errorFileSize(totalBytes: number, maxBytes: number): void {
                let totalBytesString: string = "Total file size: " + FileUtils.formatBytes(totalBytes);
                let maxBytesString: string = FileUtils.formatBytes(maxBytes);
                let detail: string = `${totalBytesString}. Max total file size: ${maxBytesString}`;
                this.toast.add({
                    severity:"error",
                    summary: "Size limit exceeded",
                    detail: detail,
                    life: 10000
                });
            }
        }
    });
</script>

<style lang="scss">
    $color-primary: $color-winter-teal;
    $color-secondary: $color-still-water;
    .upload {
        display: flex;
        align-items: center;
        flex-direction: column;
        justify-content: space-between;
        @include respond(desktop) {
            align-items: flex-start;
            flex-direction: row;
            height: 330px;
        }
    }
    .upload .file-input-container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        flex-basis: 50%;
        outline: 2px dashed $color-secondary;
        outline-offset: -10px;
        transition: outline-offset .15s ease-in-out, background-color .15s linear;
        background-color: $color-primary;
        padding: 20px 0;
        height: 100%;
        width: 100%;
        &.file-input--file-hover {
            background-color: $color-indigo-white;
            outline-color: $color-winter-teal;
            outline-offset: -20px;
        }
    }
    .upload .file-input-container .file-input {
        display: none;
    }
    .upload .file-input-container .file-input__icon {
        color: $color-secondary;
        font-size: 100px;
    }
    .upload .file-input-container button {
        margin-top: 30px;
    }
    .upload .file-table {
        justify-content: center;
        height: 250px;
        overflow: auto;
        margin-top: 30px;
        flex-basis: 45%;
        @include respond(desktop) {
            height: 100%;
            margin: 0;
        }
    }
</style>