package com.vartdalen.imagestoreweb.validation;

import javax.validation.ValidationException;

import com.vartdalen.imagestoreweb.model.Image;

public class ImageValidation {

    public static void validateForPut(Image image) {
        validateId(image);
        validateBytesForPut(image);
    }

    private static void validateId(Image image) {
        if (image.getId() > 0) { 
            throw new ValidationException(
                String.format("Value '%s' of field 'id' is invalid. Expected: null", image.getId())
            );
        }
    }

    private static void validateBytesForPut(Image image) {
        if (image.getBytes() != null && image.getBytes().length > 0) { 
            throw new ValidationException(
                String.format("Value '%s' of field 'bytes' is invalid. PUT on bytes is not supported. Expected: null", image.getBytes())
            );
        }
    }

}
