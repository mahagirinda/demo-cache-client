package com.example.cache.client.repositories.cache;

import com.example.cache.client.view.TrdTxPriceView;
import com.telkomsigma.eclears.app.common.entity.trd.TrdTxPriceEntity;
import com.telkomsigma.eclears.app.common.entity.trd.TrdTxPriceEntityPK;
import com.telkomsigma.framework.core.api.cache.repository.annotation.CacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Param;
import com.telkomsigma.framework.core.api.cache.repository.annotation.Query;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;

import java.util.List;

/**
 * Remote cache repository
 */
@CacheRepository(cacheName = "TRD_TX_PRICE", entityClass = TrdTxPriceEntity.class)
public interface TrdTxPriceCacheRepository extends IBaseCacheRepository<TrdTxPriceEntityPK, TrdTxPriceEntity> {

    @Query("FROM com.telkomsigma.eclears.app.common.entity.trd.TrdTxPriceEntity " +
            "WHERE id.INSTRUMENT_CODE = :instrumentCode " +
            "AND id.MARKET_CODE = :marketCode " +
            "AND id.PRICE_DATE = :priceDate")
    List<TrdTxPriceEntity> selectByInstrumentCodeAndMarketCodeAndPriceDateList(
            @Param String instrumentCode,
            @Param String marketCode,
            @Param Long priceDate
    );

    @Query("FROM com.telkomsigma.eclears.app.common.entity.trd.TrdTxPriceEntity " +
            "WHERE id.INSTRUMENT_CODE = \"FAST\" " +
            "AND id.MARKET_CODE = \"TN\" " +
            "AND id.PRICE_DATE = 1456938000000)")
    TrdTxPriceEntity selectByInstrumentCodeAndMarketCodeAndPriceDateHard();

    @Query("select count(uploadId) from com.telkomsigma.eclears.app.common.entity.trd.TrdTxPriceEntity")
    TrdTxPriceView count();

}
