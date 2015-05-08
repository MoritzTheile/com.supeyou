package com.hotelorga.foundation.web.client.rpc.file;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.iface.dto.FileFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCFileCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<FileDTO, FileFetchQuery> {

}