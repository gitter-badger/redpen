/**
 * redpen: a text inspection tool
 * Copyright (C) 2014 Recruit Technologies Co., Ltd. and contributors
 * (see CONTRIBUTORS.md)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cc.redpen.validator.sentence;

import cc.redpen.RedPenException;
import cc.redpen.ValidationError;
import cc.redpen.model.Sentence;
import cc.redpen.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Validate input sentences contain more characters more than specified.
 */
public class SentenceLengthValidator extends Validator<Sentence> {
    /**
     * Default maximum length of sentences.
     */
    @SuppressWarnings("WeakerAccess")
    public static final int DEFAULT_MAX_LENGTH = 30;
    private static final Logger LOG =
            LoggerFactory.getLogger(SentenceLengthValidator.class);
    private int maxLength = DEFAULT_MAX_LENGTH;

    public List<ValidationError> validate(Sentence line) {
        List<ValidationError> result = new ArrayList<>();
        if (line.content.length() > maxLength) {
            result.add(new ValidationError(
                    this.getClass(),
                    "The length of the line exceeds the maximum "
                            + String.valueOf(line.content.length()) + ".",
                    line));
        }
        return result;
    }

    @Override
    protected void init() throws RedPenException {
        this.maxLength = getConfigAttributeAsInt("max_length", DEFAULT_MAX_LENGTH);
    }

    protected void setMaxLength(int max) {
        this.maxLength = max;
    }
}
