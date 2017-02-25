package com.wondersgroup.falcon.dao.archives;

import java.io.Serializable;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.FaxNode;

public class FaxDAO extends NodeDAO {

	public FaxDAO() throws InfrastructureException {
		super();
	}

	public FaxNode findRoot() throws InfrastructureException {
		return (FaxNode) super.findRoot(FaxNode.class);
	}

	public FaxNode findById(Serializable id) throws InfrastructureException {
		return (FaxNode) super.findById(FaxNode.class, id);
	}

	public void move(FaxNode fn, int displacement)
			throws InfrastructureException {
		super.move(FaxNode.class, fn, displacement);
	}
}
