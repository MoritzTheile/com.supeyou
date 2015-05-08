package com.supeyou.crudie.web.client.rpc.file;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.FileFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCFileCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<FileDTO, FileFetchQuery> {

}