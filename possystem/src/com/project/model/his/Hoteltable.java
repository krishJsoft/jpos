package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the autonum database table.
 * 
 */
@Entity
@Table(name = "hoteltable")
public class Hoteltable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int tableid;

	@Column(length = 10)
	private String tableNo;

	@Column(length = 100)
	private String tableName;

	@Column(length = 2)
	private String status;
	
	@Column(length=5)
	private String topPosition;
	
	@Column(length=5)
	private String leftPosition;
	
	@Column(length=10)
	private String tableShape;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hoteltableareaId",nullable=true)
	private Hoteltablearea hoteltablearea;
	
	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch;

	public Hoteltable() {
	}

	public int getTableid() {
		return tableid;
	}

	public void setTableid(int tableid) {
		this.tableid = tableid;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTopPosition() {
		return topPosition;
	}

	public void setTopPosition(String topPosition) {
		this.topPosition = topPosition;
	}

	public String getLeftPosition() {
		return leftPosition;
	}

	public void setLeftPosition(String leftPosition) {
		this.leftPosition = leftPosition;
	}

	public String getTableShape() {
		return tableShape;
	}

	public void setTableShape(String tableShape) {
		this.tableShape = tableShape;
	}

	public Hoteltablearea getHoteltablearea() {
		return hoteltablearea;
	}

	public void setHoteltablearea(Hoteltablearea hoteltablearea) {
		this.hoteltablearea = hoteltablearea;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}