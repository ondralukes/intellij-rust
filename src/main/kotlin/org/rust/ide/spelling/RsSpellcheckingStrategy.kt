/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.rust.ide.spelling

import com.intellij.psi.PsiElement
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy
import com.intellij.spellchecker.tokenizer.Tokenizer
import org.rust.lang.RsLanguage
import org.rust.lang.core.psi.RsElementTypes.STRING_LITERAL
import org.rust.lang.core.psi.ext.RsNameIdentifierOwner

class RsSpellcheckingStrategy : SpellcheckingStrategy() {

    override fun isMyContext(element: PsiElement) = RsLanguage.`is`(element.language)

    override fun getTokenizer(element: PsiElement?): Tokenizer<*> = when {
        element?.node?.elementType == STRING_LITERAL -> StringLiteralTokenizer
        element is RsNameIdentifierOwner -> RsNameIdentifierOwnerTokenizer
        else -> super.getTokenizer(element)
    }
}
