package com.wondersgroup.falcon.dao.archives;

import java.io.Serializable;
import java.util.Iterator;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.CaseNode;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.VoiceNode;

public class VoiceDAO extends NodeDAO {

	public VoiceDAO() throws InfrastructureException {
		super();
	}

	public VoiceNode findRoot() throws InfrastructureException {
		return (VoiceNode) super.findRoot(VoiceNode.class);
	}

	public VoiceNode findById(Serializable id) throws InfrastructureException {
		return (VoiceNode) super.findById(VoiceNode.class, id);
	}

	public void move(VoiceNode vn, int displacement)
			throws InfrastructureException {
		super.move(VoiceNode.class, vn, displacement);
	}

	/**
	 * 设置给定节点visible或invisible
	 * edit by cjj 08-3-31
	 */
	public void setVisible(VoiceNode vn, boolean visible) 
		throws InfrastructureException {
			super.setVisible(VoiceNode.class, vn, visible);
		
	}
	public void setNodeVisible(VoiceNode vn, boolean visible) 
	throws InfrastructureException {
		
		vn.setVisible(visible);
		for (Iterator it = vn.getChildren().iterator(); it.hasNext();) {
			VoiceNode child = (VoiceNode) it.next();
			child.setVisible(visible);
			setNodeVisible(child, visible);
		}
	
}
}
