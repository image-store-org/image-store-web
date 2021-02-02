export default class RegExUtils {
    public static DIGIT: RegExp = /^[\d]$/;
    public static INTEGER: RegExp = /^[\d]+$/;
    public static URL: RegExp = /((([A-Za-z]{3,9}:(?:\/\/)?)(?:[-;:&=+$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=+$,\w]+@)[A-Za-z0-9.-]+)((?:\/[+~%\/.\w-_]*)?\??(?:[-+=&;%@.\w_]*)#?(?:[\w]*))?)/;
    public static FILE_EXTENSION: RegExp = /(?:\.([^.]+))?$/;
    public static FINAL_SUB_PATH: RegExp = /[^/]*$/;

    //Digit
    public static isDigit(input: string): boolean {
        return this.DIGIT.test(input);
    }

    //Integer of any size
    public static isInteger(input: string): boolean {
        return this.INTEGER.test(input);
    }

    //Number of any size
    public static isNumber(input: string): boolean {
        return !isNaN(parseInt(input));
    }

    public static isStringNullOrEmpty(input: string): boolean {
        return !input || input.length === 0;
    }

    public static isUrl(url: string): boolean {
        return this.URL.test(url);
    }

    public static findWordsContainingString(query: string, toSearch: string): string[] {
        //Get words containing query string. flags: global, case insensitive
        let regEx: RegExp = new RegExp(`\\w*${query}\\w*`, "gi");
        return toSearch.match(regEx) ?? [];
    }

    public static getCurrentRouteFinalSubPath(path: string): string {
        if (!this.isStringNullOrEmpty(path)) {
            return this.FINAL_SUB_PATH.exec(path)![0];
        }
        return "";
    }
}