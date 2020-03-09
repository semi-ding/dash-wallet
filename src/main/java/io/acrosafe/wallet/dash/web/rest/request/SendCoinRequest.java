/**
 * MIT License
 *
 * Copyright (c) 2020 acrosafe technologies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.acrosafe.wallet.dash.web.rest.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.acrosafe.wallet.core.dash.Passphrase;

public class SendCoinRequest
{
    @JsonProperty("recipients")
    private List<Recipient> recipients;

    @JsonProperty("internal_id")
    private String internalId;

    @JsonProperty("number_block")
    private Integer numberOfBlock;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("signing_key_passphrase")
    private Passphrase signingKeyPassphrase;

    @JsonProperty("backup_signing_key_passphrase")
    private Passphrase backupSigningKeyPassphrase;

    @JsonProperty("using_backup_signer")
    private Boolean usingBackupSigner;

    @JsonProperty("memo")
    private String memo;

    public List<Recipient> getRecipients()
    {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients)
    {
        this.recipients = recipients;
    }

    public void addRecipient(Recipient recipient)
    {
        if (recipients == null)
        {
            recipients = new ArrayList<>();
        }

        recipients.add(recipient);
    }

    public String getInternalId()
    {
        return internalId;
    }

    public void setInternalId(String internalId)
    {
        this.internalId = internalId;
    }

    public Integer getNumberOfBlock()
    {
        return numberOfBlock;
    }

    public void setNumberOfBlock(Integer numberOfBlock)
    {
        this.numberOfBlock = numberOfBlock;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public Boolean getUsingBackupSigner()
    {
        return usingBackupSigner;
    }

    public void setUsingBackupSigner(Boolean usingBackupSigner)
    {
        this.usingBackupSigner = usingBackupSigner;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public Passphrase getSigningKeyPassphrase()
    {
        return signingKeyPassphrase;
    }

    public void setSigningKeyPassphrase(String signingKeyPassphrase)
    {
        this.signingKeyPassphrase = new Passphrase(signingKeyPassphrase);
    }

    public Passphrase getBackupSigningKeyPassphrase()
    {
        return backupSigningKeyPassphrase;
    }

    public void setBackupSigningKeyPassphrase(String backupSigningKeyPassphrase)
    {
        this.backupSigningKeyPassphrase = new Passphrase(backupSigningKeyPassphrase);
    }
}
