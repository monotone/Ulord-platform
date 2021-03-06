/**
 * Copyright(c) 2018
 * Ulord core developers
 */
package one.ulord.upaas.uauth.server.contentauth.dao;

import one.ulord.upaas.uauth.server.contentauth.vo.SensitiveWordItem;

import java.util.List;
import java.util.Map;

/**
 * @author haibo
 * @since 5/26/18
 */
public interface SensitiveWordMapper {
    int createItem(SensitiveWordItem value);
    int updateItem(SensitiveWordItem value);
    int deleteItem(long uid);

    SensitiveWordItem retrieveItem(long uid);
    List<SensitiveWordItem> loadActive();
    List<SensitiveWordItem> retrieve(Map<String, List<String>> criteria);

    int disableItem(long uid);
    int enableItem(long uid);
}
