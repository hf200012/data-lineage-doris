package com.eyesmoons.lineage.event.domain.model;

import com.eyesmoons.lineage.event.contants.NeoConstant;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Properties;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Unified abstraction of graph relationships and node entities
 */
@Setter
@Getter
public abstract class BaseEntity {
    /**
     * Figure real ID，Generated by neo4j engine，Can't be modified
     */
    private Long id;

    /**
     * Node extra value, starting with ext prefix
     */
    @Properties(prefix = NeoConstant.ENTITY_EXTRA_PREFIX)
    private Map<String, String> extra;

    public Map<String, String> getExtra() {
        if (CollectionUtils.isEmpty(extra)) {
            synchronized (this) {
                if (CollectionUtils.isEmpty(extra)) {
                    this.extra = new HashMap<>(16);
                }
            }
        }
        return this.extra;
    }

}