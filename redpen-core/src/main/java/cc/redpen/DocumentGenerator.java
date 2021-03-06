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
package cc.redpen;

import cc.redpen.config.Configuration;
import cc.redpen.model.DocumentCollection;
import cc.redpen.parser.DocumentParserFactory;
import cc.redpen.parser.Parser;

/**
 * Generate DocumentCollection object loading input file.
 */
public final class DocumentGenerator {
    private DocumentGenerator() {
        super();
    }

    /**
     * Generate DocumentCollection from input file.
     *
     * @param inputFileNames input file name
     * @param configuration  configuration configuration
     * @param format         input file format
     * @return a generated DocumentCollection object
     */
    static DocumentCollection generate(String[] inputFileNames,
                                       Configuration configuration,
                                       Parser.Type format) throws RedPenException {
        DocumentCollection.Builder documentBuilder =
                new DocumentCollection.Builder();
        Parser parser = DocumentParserFactory.generate(format,
                configuration, documentBuilder);

        for (String inputFileName : inputFileNames) {
            parser.generateDocument(inputFileName);
        }
        // @TODO extract summary information to validate documentCollection effectively
        return documentBuilder.build();
    }
}
