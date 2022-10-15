package org.cloud.federation.utils;

import java.util.Vector;

public class OVFUtil {
	
	public static Envelope ovf_CfpToEnvelope (String file_ovf)
	{
		Envelope e = new Envelope();
		e.setBuild(OVFUtil.virtualSystem_Envelope_Build(file_ovf));
		e.setSession(OVFUtil.virtualSystem_Envelope_Session(file_ovf));
		e.setReceive(OVFUtil.virtualSystem_Envelope_Receive(file_ovf));
		e.setType(OVFUtil.virtualSystem_Envelope_Type(file_ovf));
		e.setVMs(OVFUtil.ovf_CfpToListVirtualMachine(file_ovf));
		return e;
	}
	
	public static Envelope ovf_ProposalToEnvelope (String file_ovf)
	{
		Envelope e = new Envelope();
		e.setBuild(OVFUtil.virtualSystem_Envelope_Build(file_ovf));
		e.setSession(OVFUtil.virtualSystem_Envelope_Session(file_ovf));
		e.setReceive(OVFUtil.virtualSystem_Envelope_Receive(file_ovf));
		e.setType(OVFUtil.virtualSystem_Envelope_Type(file_ovf));
		e.setVMs(OVFUtil.ovf_ProposalToListVirtualMachine(file_ovf));
		return e;
	}
	
	 public static Vector<VirtualMachine> ovf_CfpToListVirtualMachine (String file_ovf)
	{
		Vector<VirtualMachine> vm_cfp= new Vector<VirtualMachine> ();

		Vector<String> VSs = OVFUtil.listVirtualSystem(file_ovf);	
		for (int i = 0; i < VSs.size(); i++) {
			String vs = (String) VSs.get(i);
			VirtualMachine v = new VirtualMachine();
			//v.setLogin(OVFUtil.get_US_Login(ovf_file));
			//v.setPassword(OVFUtil.get_US_Password(ovf_file));
			v.setOS(OVFUtil.virtualSystem_OSS_Description(vs));	
			AppProductor app=new AppProductor(OVFUtil.virtualSystem_PS_Product(vs));
			v.setApplication(app);
			v.setCPU(OVFUtil.get_VHS_Item_CPU(vs));
			v.setRAM(OVFUtil.get_VHS_Item_RAM(vs));
			v.setStorage(Double.parseDouble(OVFUtil
					.virtualSystem_VHS_StorageItem(vs)));
			vm_cfp.addElement(v);
		}
		return vm_cfp;	
	}
	
	
	 /*
	  * ovf_ProposalToListVirtualMachine
	  */
	 public static Vector<VirtualMachine> ovf_ProposalToListVirtualMachine (String file_ovf)
		{
			Vector<VirtualMachine> vm_proposal= new Vector<VirtualMachine> ();
			
			
			Vector<String> VSs = OVFUtil.listVirtualSystem(file_ovf);	
			for (int i = 0; i < VSs.size(); i++) {
				String vs = (String) VSs.get(i);
				VirtualMachine v = new VirtualMachine();
				//v.setLogin(OVFUtil.get_US_Login(ovf_file));
				//v.setPassword(OVFUtil.get_US_Password(ovf_file));
				v.setOS(OVFUtil.virtualSystem_OSS_Description(vs));	
				AppProductor app=new AppProductor(OVFUtil.virtualSystem_PS_Product(vs));
				v.setApplication(app);
				v.setCPU(OVFUtil.get_VHS_Item_CPU(vs));
				v.setRAM(OVFUtil.get_VHS_Item_RAM(vs));
				v.setStorage(Double.parseDouble(OVFUtil
						.virtualSystem_VHS_StorageItem(vs)));
				v.setPrice(OVFUtil.get_US_Prive(vs));
				vm_proposal.addElement(v);
			}
			return vm_proposal;	
		}
	 
	 
	 /*
	  * ovf_AcceptToListVirtualMachine
	  */
	 public static Vector<VirtualMachine> ovf_AcceptToListVirtualMachine (String ovf_file)
		{
			Vector<VirtualMachine> vm_proposal= new Vector<VirtualMachine> ();
			
			if(ovf_file!=null)
			{
			Vector<String> VSs = OVFUtil.listVirtualSystem(ovf_file);	
			for (int i = 0; i < VSs.size(); i++) {
				String vs = (String) VSs.get(i);
				VirtualMachine v = new VirtualMachine();
				v.setLogin(OVFUtil.get_US_Login(ovf_file));
				v.setPassword(OVFUtil.get_US_Password(ovf_file));
				v.setOS(OVFUtil.virtualSystem_OSS_Description(vs));	
				AppProductor app=new AppProductor(OVFUtil.virtualSystem_PS_Product(vs));
				v.setApplication(app);
				v.setCPU(OVFUtil.get_VHS_Item_CPU(vs));
				v.setRAM(OVFUtil.get_VHS_Item_RAM(vs));
				v.setStorage(Double.parseDouble(OVFUtil
						.virtualSystem_VHS_StorageItem(vs)));
				v.setPrice(OVFUtil.get_US_Prive(vs));
				vm_proposal.addElement(v);
			}
			System.out.println("ovf_AcceptToListVirtualMachine= "+vm_proposal.size());
			}
			return vm_proposal;	
		}
	 
	 
	 /*
	  * ovf_AcceptToListVirtualMachine
	  */
	 public static Vector<VirtualMachine> ovf_InformToListVirtualMachine (String ovf_file)
		{
			Vector<VirtualMachine> vm_proposal= new Vector<VirtualMachine> ();
			
			
			Vector<String> VSs = OVFUtil.listVirtualSystem(ovf_file);
			if(VSs!=null){
			for (int i = 0; i < VSs.size(); i++) {
				String vs = (String) VSs.get(i);
				VirtualMachine v = new VirtualMachine();
				v.setLogin(OVFUtil.get_US_Login(ovf_file));
				v.setPassword(OVFUtil.get_US_Password(ovf_file));
				v.setOS(OVFUtil.virtualSystem_OSS_Description(vs));	
				AppProductor app=new AppProductor(OVFUtil.virtualSystem_PS_Product(vs));
				v.setApplication(app);
				v.setCPU(OVFUtil.get_VHS_Item_CPU(vs));
				v.setRAM(OVFUtil.get_VHS_Item_RAM(vs));
				v.setStorage(Double.parseDouble(OVFUtil
						.virtualSystem_VHS_StorageItem(vs)));
				v.setPrice(OVFUtil.get_US_Prive(vs));
				
				Graphic graphic= new Graphic();
				graphic.setListen(OVFUtil.virtualSystem_Graphic_LISTEN(vs));
				graphic.setPort(OVFUtil.virtualSystem_Graphic_PORT(vs));
				graphic.setType(OVFUtil.virtualSystem_Graphic_TYPE(vs));
				v.setGraphic(graphic);
				
				NIC nic= new NIC();
				nic.setNetwork(OVFUtil.virtualSystem_NIC_Network(vs));
				nic.setIp(OVFUtil.virtualSystem_NIC_IP(vs));
				nic.setIp6(OVFUtil.virtualSystem_NIC_IP6(vs));
				nic.setMac(OVFUtil.virtualSystem_NIC_MAC(vs));
				v.setNic(nic);
				vm_proposal.addElement(v);
			}
			}
			return vm_proposal;	
		}
	 
	 
	/*
	 * have OVF file: type CFP 
	 */
	 public static String listVirtualMachineToOvf_Cfp (Envelope p)
	{
		String ovf_file=null;
		
		
		String head_ovf="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"\n<Envelope"+
				"\ncf:session=\""+p.getSession()+"\""+
				"\ncf:builID=\""+p.getBuild()+"\"" +
				"\ncf:receiveID=\""+p.getReceive()+"\"" +
				"\ncf:type=\"cfp\""+
				"\nxsi:schemaLocation=\"http://schemas.dmtf.org/ovf/envelope/2 file:///C:/dsp8023_2.0.0_wgv0.9.5.xsd\"" +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ovf=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns:vssd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_VirtualSystemSettingData\""+
				"xmlns:rasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_ResourceAllocationSettingData\""+
				"xmlns:epasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_EthernetPortAllocationSettingData\""+
				"xmlns:sasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_StorageAllocationSettingData\">" +
				"\n<References>"+
				"\n</References>";
		
		String vms_ovf="";
				for (int i= 0; i<p.getVMs().size(); i++ )
				{
					vms_ovf+="<VirtualSystem  ovf:id=\"vm"+i+"\">" +
				"\n <Info>"+p.getVMs().get(i).getName()+"</Info>"+
				"\n<Name>"+ p.getVMs().get(i).getName()  +"</Name>"+
					"\n<ProductSection>"+
						"\n<Info>"+p.getVMs().get(i).getInfo()+"</Info>"+
						"\n<Product>"+p.getVMs().get(i).getApplication().getProduct()+"</Product>"+
						"\n<Vendor>"+p.getVMs().get(i).getApplication().getVendor()+"</Vendor>"+
						"\n<Version>"+p.getVMs().get(i).getApplication().getVersion()+"</Version>"+
						"\n<FullVersion>"+p.getVMs().get(i).getApplication().getFullVersion()+"</FullVersion>"+
					"\n</ProductSection>"+	
						
					"\n<OperatingSystemSection ovf:required=\"true\">"+
						"\n<Info> Guest Operating System</Info>"+
						"\n<Description>"+ p.getVMs().get(i).getOS() +"</Description>"+
					"\n</OperatingSystemSection>"+	
						
					"\n<VirtualHardwareSection>"+
					"\n<Info>Virtual hardware requirements</Info>"+
					
					"\n<Item>"+
					"\n<rasd:AllocationUnits>hertz * 10^6</rasd:AllocationUnits>"+
					"\n<rasd:Description>Number of Virtual CPUs</rasd:Description>"+
					"\n<rasd:ElementName>"+p.getVMs().get(i).getCPU()+" virtual CPU(s)</rasd:ElementName>"+
					"\n<rasd:InstanceID>1</rasd:InstanceID>"+
					"\n<rasd:ResourceType>3</rasd:ResourceType>"+
					"\n<rasd:VirtualQuantity>"+p.getVMs().get(i).getCPU()+"</rasd:VirtualQuantity>"+
					"\n</Item>"+
				
					"\n<Item>"+
					"\n<rasd:AllocationUnits>byte * 2^20</rasd:AllocationUnits>"+
					"\n<rasd:Description>Memory Size</rasd:Description>"+
					"\n<rasd:ElementName>"+p.getVMs().get(i).getRAM()+"MB of memory</rasd:ElementName>"+
					"\n<rasd:InstanceID>2</rasd:InstanceID>"+
					"\n<rasd:ResourceType>4</rasd:ResourceType>"+
					"\n<rasd:VirtualQuantity>"+p.getVMs().get(i).getRAM()+"</rasd:VirtualQuantity>"+
					"\n</Item>"+
					
					"\n<StorageItem>"+
					"\n<sasd:AllocationUnits>byte*2^30</sasd:AllocationUnits>"+
					"\n<sasd:Description>Virtual Disk</sasd:Description>"+
					"\n<sasd:ElementName>"+p.getVMs().get(i).getStorage()+" GByte Virtual Disk</sasd:ElementName>"+
					"\n<sasd:InstanceID>4</sasd:InstanceID>"+
					"\n<sasd:Reservation>"+p.getVMs().get(i).getStorage()+"</sasd:Reservation>"+
					"\n<sasd:ResourceType>5</sasd:ResourceType>"+
					"\n<sasd:VirtualQuantity>1</sasd:VirtualQuantity>"+
					"\n</StorageItem>"+
					"\n</VirtualHardwareSection>"+
					"\n</VirtualSystem>";
				}
				
		String end_ovf="\n</Envelope>";
		ovf_file=head_ovf+vms_ovf+end_ovf;	
		return ovf_file;	
	}
	
	
	/*
	 * have OVF file: type Propose 
	 */
	public static String listVirtualMachineToOvf_Proposal (Envelope p)
	{
		String ovf_file=null;
		
		
		String head_ovf="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"\n<Envelope"+
				"\ncf:session=\""+p.getSession()+"\"" +
				"\ncf:builID=\""+p.getBuild()+"\"" +
				"\ncf:receiveID=\""+p.getReceive()+"\""+
				"\ncf:type=\"proposal\""+
				"\nxsi:schemaLocation=\"http://schemas.dmtf.org/ovf/envelope/2 file:///C:/dsp8023_2.0.0_wgv0.9.5.xsd\"" +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ovf=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns:vssd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_VirtualSystemSettingData\""+
				"xmlns:rasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_ResourceAllocationSettingData\""+
				"xmlns:epasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_EthernetPortAllocationSettingData\""+
				"xmlns:sasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_StorageAllocationSettingData\">" +
				"\n<References>"+
				"\n</References>";
		
		String vms_ovf="";
				for (int i= 0; i<p.getVMs().size(); i++ )
				{
					vms_ovf+="<VirtualSystem   ovf:id=\"vm"+i+"\">" +
				"\n <Info>"+p.getVMs().get(i).getName()+"</Info>"+
				"\n<Name>"+ p.getVMs().get(i).getName()  +"</Name>"+
					"\n<ProductSection>"+
						"\n<Info>"+p.getVMs().get(i).getInfo()+"</Info>"+
						"\n<Product>"+p.getVMs().get(i).getApplication().getProduct()+"</Product>"+
						"\n<Vendor>"+p.getVMs().get(i).getApplication().getVendor()+"</Vendor>"+
						"\n<Version>"+p.getVMs().get(i).getApplication().getVersion()+"</Version>"+
						"\n<FullVersion>"+p.getVMs().get(i).getApplication().getFullVersion()+"</FullVersion>"+
					"\n</ProductSection>"+	
						
					"\n<OperatingSystemSection ovf:required=\"true\">"+
						"\n<Info> Guest Operating System</Info>"+
						"\n<Description>"+ p.getVMs().get(i).getOS() +"</Description>"+
					"\n</OperatingSystemSection>"+	
						
					"\n<VirtualHardwareSection>"+
					"\n<Info>Virtual hardware requirements</Info>"+
					
					"\n<Item>"+
					"\n<rasd:AllocationUnits>hertz * 10^6</rasd:AllocationUnits>"+
					"\n<rasd:Description>Number of Virtual CPUs</rasd:Description>"+
					"\n<rasd:ElementName>"+p.getVMs().get(i).getCPU()+" virtual CPU(s)</rasd:ElementName>"+
					"\n<rasd:InstanceID>1</rasd:InstanceID>"+
					"\n<rasd:ResourceType>3</rasd:ResourceType>"+
					"\n<rasd:VirtualQuantity>"+p.getVMs().get(i).getCPU()+"</rasd:VirtualQuantity>"+
					"\n</Item>"+
				
					"\n<Item>"+
					"\n<rasd:AllocationUnits>byte * 2^20</rasd:AllocationUnits>"+
					"\n<rasd:Description>Memory Size</rasd:Description>"+
					"\n<rasd:ElementName>"+p.getVMs().get(i).getRAM()+"MB of memory</rasd:ElementName>"+
					"\n<rasd:InstanceID>2</rasd:InstanceID>"+
					"\n<rasd:ResourceType>4</rasd:ResourceType>"+
					"\n<rasd:VirtualQuantity>"+p.getVMs().get(i).getRAM()+"</rasd:VirtualQuantity>"+
					"\n</Item>"+
					
					"\n<StorageItem>"+
					"\n<sasd:AllocationUnits>byte*2^30</sasd:AllocationUnits>"+
					"\n<sasd:Description>Virtual Disk</sasd:Description>"+
					"\n<sasd:ElementName>"+p.getVMs().get(i).getStorage()+" GByte Virtual Disk</sasd:ElementName>"+
					"\n<sasd:InstanceID>4</sasd:InstanceID>"+
					"\n<sasd:Reservation>"+p.getVMs().get(i).getStorage()+"</sasd:Reservation>"+
					"\n<sasd:ResourceType>5</sasd:ResourceType>"+
					"\n<sasd:VirtualQuantity>1</sasd:VirtualQuantity>"+
					"\n</StorageItem>"+
					"\n</VirtualHardwareSection>"+
					"\n<QoSSection>"+
					"\n<Price ovf:unit=\"$\" ovf:perid=\"per Hour\">"+p.getVMs().get(i).getPrice()+"</Price>"+
					"\n</QoSSection>"+
					"\n</VirtualSystem>";
				}
				
		String end_ovf="\n</Envelope>";
		ovf_file=head_ovf+vms_ovf+end_ovf;	
		return ovf_file;	
	}
	
	
	
	
	
	
	public static String listVirtualMachineToOvf_Accept (Envelope p)
	{
		String ovf_file=null;
		String head_ovf="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"\n<Envelope"+
				"\ncf:session=\""+p.getSession()+"\"" +
				"\ncf:builID=\""+p.getBuild()+"\"" +
				"\ncf:receiveID=\""+p.getReceive()+"\""+
				"\ncf:type=\"accept\""+
				"\nxsi:schemaLocation=\"http://schemas.dmtf.org/ovf/envelope/2 file:///C:/dsp8023_2.0.0_wgv0.9.5.xsd\"" +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ovf=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns:vssd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_VirtualSystemSettingData\""+
				"xmlns:rasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_ResourceAllocationSettingData\""+
				"xmlns:epasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_EthernetPortAllocationSettingData\""+
				"xmlns:sasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_StorageAllocationSettingData\">" +
				"\n<References>"+
				"\n</References>"+
				
				"\n<UserSection>"+
				"\n<Login>"+p.getLogin()+"</Login>"+
				"\n<Password>"+p.getPassword()+"</Password>"+		
				"\n</UserSection>";	
		
		String vms_ovf="";
				for (int i= 0; i<p.getVMs().size(); i++ )
				{
					vms_ovf+="\n<VirtualSystem   ovf:id=\"vm"+i+"\">" +
				"\n <Info>"+p.getVMs().get(i).getName()+"</Info>"+
				"\n<Name>"+ p.getVMs().get(i).getName()  +"</Name>"+
					"\n<ProductSection>"+
						"\n<Info>"+p.getVMs().get(i).getInfo()+"</Info>"+
						"\n<Product>"+p.getVMs().get(i).getApplication().getProduct()+"</Product>"+
						"\n<Vendor>"+p.getVMs().get(i).getApplication().getVendor()+"</Vendor>"+
						"\n<Version>"+p.getVMs().get(i).getApplication().getVersion()+"</Version>"+
						"\n<FullVersion>"+p.getVMs().get(i).getApplication().getFullVersion()+"</FullVersion>"+
					"\n</ProductSection>"+	
						
					"\n<OperatingSystemSection ovf:required=\"true\">"+
						"\n<Info> Guest Operating System</Info>"+
						"\n<Description>"+ p.getVMs().get(i).getOS() +"</Description>"+
					"\n</OperatingSystemSection>"+	
						
					"\n<VirtualHardwareSection>"+
					"\n<Info>Virtual hardware requirements</Info>"+
					
					"\n<Item>"+
					"\n<rasd:AllocationUnits>hertz * 10^6</rasd:AllocationUnits>"+
					"\n<rasd:Description>Number of Virtual CPUs</rasd:Description>"+
					"\n<rasd:ElementName>"+p.getVMs().get(i).getCPU()+" virtual CPU(s)</rasd:ElementName>"+
					"\n<rasd:InstanceID>1</rasd:InstanceID>"+
					"\n<rasd:ResourceType>3</rasd:ResourceType>"+
					"\n<rasd:VirtualQuantity>"+p.getVMs().get(i).getCPU()+"</rasd:VirtualQuantity>"+
					"\n</Item>"+
				
					"\n<Item>"+
					"\n<rasd:AllocationUnits>byte * 2^20</rasd:AllocationUnits>"+
					"\n<rasd:Description>Memory Size</rasd:Description>"+
					"\n<rasd:ElementName>"+p.getVMs().get(i).getRAM()+"MB of memory</rasd:ElementName>"+
					"\n<rasd:InstanceID>2</rasd:InstanceID>"+
					"\n<rasd:ResourceType>4</rasd:ResourceType>"+
					"\n<rasd:VirtualQuantity>"+p.getVMs().get(i).getRAM()+"</rasd:VirtualQuantity>"+
					"\n</Item>"+
					
					"\n<StorageItem>"+
					"\n<sasd:AllocationUnits>byte*2^30</sasd:AllocationUnits>"+
					"\n<sasd:Description>Virtual Disk</sasd:Description>"+
					"\n<sasd:ElementName>"+p.getVMs().get(i).getStorage()+" GByte Virtual Disk</sasd:ElementName>"+
					"\n<sasd:InstanceID>4</sasd:InstanceID>"+
					"\n<sasd:Reservation>"+p.getVMs().get(i).getStorage()+"</sasd:Reservation>"+
					"\n<sasd:ResourceType>5</sasd:ResourceType>"+
					"\n<sasd:VirtualQuantity>1</sasd:VirtualQuantity>"+
					"\n</StorageItem>"+
					"\n</VirtualHardwareSection>"+
					"\n<QoSSection>"+
					"\n<Price ovf:unit=\"$\" ovf:perid=\"per Hour\">"+p.getVMs().get(i).getPrice()+"</Price>"+
					"\n</QoSSection>"+
					"\n</VirtualSystem>";
				}
				
		String end_ovf="\n</Envelope>";
		ovf_file=head_ovf+vms_ovf+end_ovf;	
		return ovf_file;	
	}
	
	
	
	public static String listVirtualMachineToOvf_Info (Envelope p)
	{
		String ovf_file=null;
		
		
		String head_ovf="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"\n<Envelope"+
				"\ncf:session=\""+p.getSession()+"\"" +
				"\ncf:builID=\""+p.getBuild()+"\"" +
				"\ncf:receiveID=\""+p.getReceive()+"\""+
				"\ncf:type=\"inform\""+
				"\nxsi:schemaLocation=\"http://schemas.dmtf.org/ovf/envelope/2 file:///C:/dsp8023_2.0.0_wgv0.9.5.xsd\"" +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ovf=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns:vssd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_VirtualSystemSettingData\""+
				"xmlns:rasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_ResourceAllocationSettingData\""+
				"xmlns:epasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_EthernetPortAllocationSettingData\""+
				"xmlns:sasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_StorageAllocationSettingData\">" +
				"\n<References>"+
				"\n</References>"+
				
				"\n<UserSection>"+
				"\n<Login>"+p.getLogin()+"</Login>"+
				"\n<Password>"+p.getPassword()+"</Password>"+		
				"\n</UserSection>";	
		
		String vms_ovf="";
				for (int i= 0; i<p.getVMs().size(); i++ )
				{
					vms_ovf+="\n<VirtualSystem   ovf:id=\"vm"+i+"\">" +
				"\n <Info>"+p.getVMs().get(i).getName()+"</Info>"+
				"\n<Name>"+ p.getVMs().get(i).getName()  +"</Name>"+
					"\n<ProductSection>"+
						"\n<Info>"+p.getVMs().get(i).getInfo()+"</Info>"+
						"\n<Product>"+p.getVMs().get(i).getApplication().getProduct()+"</Product>"+
						"\n<Vendor>"+p.getVMs().get(i).getApplication().getVendor()+"</Vendor>"+
						"\n<Version>"+p.getVMs().get(i).getApplication().getVersion()+"</Version>"+
						"\n<FullVersion>"+p.getVMs().get(i).getApplication().getFullVersion()+"</FullVersion>"+
					"\n</ProductSection>"+	
						
					"\n<OperatingSystemSection ovf:required=\"true\">"+
						"\n<Info> Guest Operating System</Info>"+
						"\n<Description>"+ p.getVMs().get(i).getOS() +"</Description>"+
					"\n</OperatingSystemSection>"+	
						
					"\n<VirtualHardwareSection>"+
					"\n<Info>Virtual hardware requirements</Info>"+
					
					"\n<Item>"+
					"\n<rasd:AllocationUnits>hertz * 10^6</rasd:AllocationUnits>"+
					"\n<rasd:Description>Number of Virtual CPUs</rasd:Description>"+
					"\n<rasd:ElementName>"+p.getVMs().get(i).getCPU()+" virtual CPU(s)</rasd:ElementName>"+
					"\n<rasd:InstanceID>1</rasd:InstanceID>"+
					"\n<rasd:ResourceType>3</rasd:ResourceType>"+
					"\n<rasd:VirtualQuantity>"+p.getVMs().get(i).getCPU()+"</rasd:VirtualQuantity>"+
					"\n</Item>"+
				
					"\n<Item>"+
					"\n<rasd:AllocationUnits>byte * 2^20</rasd:AllocationUnits>"+
					"\n<rasd:Description>Memory Size</rasd:Description>"+
					"\n<rasd:ElementName>"+p.getVMs().get(i).getRAM()+"MB of memory</rasd:ElementName>"+
					"\n<rasd:InstanceID>2</rasd:InstanceID>"+
					"\n<rasd:ResourceType>4</rasd:ResourceType>"+
					"\n<rasd:VirtualQuantity>"+p.getVMs().get(i).getRAM()+"</rasd:VirtualQuantity>"+
					"\n</Item>"+
					
					"\n<StorageItem>"+
					"\n<sasd:AllocationUnits>byte*2^30</sasd:AllocationUnits>"+
					"\n<sasd:Description>Virtual Disk</sasd:Description>"+
					"\n<sasd:ElementName>"+p.getVMs().get(i).getStorage()+" GByte Virtual Disk</sasd:ElementName>"+
					"\n<sasd:InstanceID>4</sasd:InstanceID>"+
					"\n<sasd:Reservation>"+p.getVMs().get(i).getStorage()+"</sasd:Reservation>"+
					"\n<sasd:ResourceType>5</sasd:ResourceType>"+
					"\n<sasd:VirtualQuantity>1</sasd:VirtualQuantity>"+
					"\n</StorageItem>"+
					"\n</VirtualHardwareSection>"+
					"\n<QoSSection>"+
					
					"\n<NICsSection>"+
					"\n<NIC>"+
					"\n<Network>"+p.getVMs().get(i).getNic().getNetwork()+"</Network>"+
					"\n<IP>"+p.getVMs().get(i).getNic().getIp()+"</IP>"+
					"\n<IP6>"+p.getVMs().get(i).getNic().getIp6()+"</IP6>"+
					"\n<MAC>"+p.getVMs().get(i).getNic().getMac()+"</MAC>"+
					"\n</NIC>"+
					"\n</NICsSection>"+
					
					"\n<GraphicSection>"+
					"\n<Type>"+p.getVMs().get(i).getGraphic().getType()+"</Type>"+
					"\n<Listen>"+p.getVMs().get(i).getGraphic().getListen()+"</Listen>"+
					"\n<Port>"+p.getVMs().get(i).getGraphic().getPort()+"</Port>"+
					"\n</GraphicSection>"+
					
					"\n<QoSSection>"+
					"\n<Price ovf:unit=\"$\" ovf:perid=\"per Hour\">"+p.getVMs().get(i).getPrice()+"</Price>"+
					"\n</QoSSection>"+
					"\n</VirtualSystem>";
				}
				
		String end_ovf="\n</Envelope>";
		ovf_file=head_ovf+vms_ovf+end_ovf;	
		return ovf_file;	
	}
	
	
	
	public static String virtualSystem_Envelope_Session(String ovf_file)
	{
		
		int i= ovf_file.indexOf("<Envelope");
		int j=ovf_file.indexOf("<References>");
		 String ps=ovf_file.substring(i, j);
		 i= ps.indexOf("cf:session=");
		 j=ps.indexOf("cf:builID=");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("cf:session=", "");
		 v=v.replaceAll("\n", "");
		 v=v.replaceAll("\"", "");
		 v=v.replaceAll("\\s", "");
		return v;
	}
	
	public static String  virtualSystem_Envelope_Build(String ovf_file)
	{
		
		int i= ovf_file.indexOf("<Envelope");
		int j=ovf_file.indexOf("<References>");
		 String ps=ovf_file.substring(i, j);
		 i= ps.indexOf("cf:builID=");
		 j=ps.indexOf("cf:receiveID=");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("cf:builID=", "");
		 v=v.replaceAll("\n", "");
		 v=v.replaceAll("\"", "");
		 v=v.replaceAll("\\s", "");
		
		return v;
	}
	
	public static String  virtualSystem_Envelope_Receive(String ovf_file)
	{
		
		int i= ovf_file.indexOf("<Envelope");
		int j=ovf_file.indexOf("<References>");
		 String ps=ovf_file.substring(i, j);
		 i= ps.indexOf("cf:receiveID=");
		 j=ps.indexOf("cf:type=");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("cf:receiveID=", "");
		 v=v.replaceAll("\n", "");
		 v=v.replaceAll("\"", "");
		 v=v.replaceAll("\\s", "");
		
		return v;
	}
	public static String  virtualSystem_Envelope_Type(String ovf_file)
	{
		
		int i= ovf_file.indexOf("<Envelope");
		int j=ovf_file.indexOf("<References>");
		 String ps=ovf_file.substring(i, j);
		 i= ps.indexOf("cf:type=");
		 j=ps.indexOf("xsi:schemaLocation");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("cf:type=", "");
		 v=v.replaceAll("\n", "");
		 v=v.replaceAll("\"", "");
		 v=v.replaceAll("\\s", "");
		
		return v;
	}
	/*
	public static void main (String args[])
	{
		String head_ovf="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"\n<Envelope"+
				"\novf:builID=\"build_12547\"" +
				"\novf:receiveID=\"provider02\""+
				"\novf:type=\"inform\""+
				"\nxsi:schemaLocation=\"http://schemas.dmtf.org/ovf/envelope/2 file:///C:/dsp8023_2.0.0_wgv0.9.5.xsd\"" +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ovf=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns=\"http://schemas.dmtf.org/ovf/envelope/2\""+
				"xmlns:vssd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_VirtualSystemSettingData\""+
				"xmlns:rasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_ResourceAllocationSettingData\""+
				"xmlns:epasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_EthernetPortAllocationSettingData\""+
				"xmlns:sasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_StorageAllocationSettingData\">" +
				"\n<References>"+
				"\n</References>"+
				
				"\n<UserSection>";
		
		System.out.println("la resultat="+virtualSystem_Envelope_Build(head_ovf));
		System.out.println("la resultat="+virtualSystem_Envelope_Receive(head_ovf));
				
	}
	*/
	
	
	
	
	public static Vector<String>  listVirtualSystem(String fileOVF)
	{
		Vector<String> VSs = new Vector<String>();
		String chaine="<VirtualSystem ";
		
		while(fileOVF.contains(chaine)){
		int i=fileOVF.indexOf("<VirtualSystem ");
		int j= fileOVF.indexOf("</VirtualSystem>");
		String vs= fileOVF.substring(i, j);
		//vs=vs.replaceAll("<Product>", "");
		//System.out.println(vs);
		VSs.addElement(vs);
		fileOVF=fileOVF.replaceFirst("<VirtualSystem ", "");
		fileOVF=fileOVF.replaceFirst("</VirtualSystem>", "");
		}
		return VSs;
	}
	
	public static String  virtualSystem_PS_Product(String vs)
	{
		
		int i= vs.indexOf("<ProductSection>");
		int j=vs.indexOf("</ProductSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Product>");
		 j=ps.indexOf("</Product>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Product>", "");
		
		return v;
	}
	
	
	
	public static String virtualSystem_PS_Vendor(String vs)
	{
		
		int i= vs.indexOf("<ProductSection>");
		int j=vs.indexOf("</ProductSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Vendor>");
		 j=ps.indexOf("</Vendor>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Vendor>", "");
		
		return v;
	}
	
	public static String get_US_Login(String ovf)
	{
		
		int i= ovf.indexOf("<UserSection>");
		int j=ovf.indexOf("</UserSection>");
		 String ps=ovf.substring(i, j);
		 i= ps.indexOf("<Login>");
		 j=ps.indexOf("</Login>");
		 String login=ps.substring(i, j);
		 login=login.replaceAll("<Login>", "");
		
		return login;
	}
	
	public static String get_US_Password(String ovf)
	{
		
		int i= ovf.indexOf("<UserSection>");
		int j=ovf.indexOf("</UserSection>");
		 String ps=ovf.substring(i, j);
		 i= ps.indexOf("<Password>");
		 j=ps.indexOf("</Password>");
		 String password=ps.substring(i, j);
		 password=password.replaceAll("<Password>", "");
		
		return password;
	}
	
	
	
	
	
	
	
	
	
	public static double get_US_Prive(String vs)
	{
		double price=0.0;
		int i= vs.indexOf("<QoSSection>");
		int j=vs.indexOf("</QoSSection>");
		 String ps=vs.substring(i, j);
		
		 i= ps.indexOf("<Price ");
		 j=ps.indexOf("</Price>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Price ", "");
		 int k= v.indexOf(">");
		 String p=v.substring(k, v.length());
		 p=p.replaceAll(">", "");
		 price= Double.parseDouble(p);
		return price;
	}
	
	
	public static String  virtualSystem_PS_Version(String vs)
	{
		

		int i= vs.indexOf("<ProductSection>");
		int j=vs.indexOf("</ProductSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Version>");
		 j=ps.indexOf("</Version>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Version>", "");
		
		return v;
	}
	
	
	
	public static String  virtualSystem_PS_FullVersion(String vs)
	{

		int i= vs.indexOf("<ProductSection>");
		int j=vs.indexOf("</ProductSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<FullVersion>");
		 j=ps.indexOf("</FullVersion>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<FullVersion>", "");
		
		return v;
	}
	
	
	
	
	public static String  virtualSystem_Graphic_LISTEN(String vs)
	{

		int i= vs.indexOf("<GraphicSection>");
		int j=vs.indexOf("</GraphicSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Listen>");
		 j=ps.indexOf("</Listen>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Listen>", "");
		
		return v;
	}
	
	public static String  virtualSystem_Graphic_PORT(String vs)
	{

		int i= vs.indexOf("<GraphicSection>");
		int j=vs.indexOf("</GraphicSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Port>");
		 j=ps.indexOf("</Port>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Port>", "");		
		return v;
	}
	
	
	public static String  virtualSystem_Graphic_TYPE(String vs)
	{

		int i= vs.indexOf("<GraphicSection>");
		int j=vs.indexOf("</GraphicSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Type>");
		 j=ps.indexOf("</Type>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Type>", "");		
		return v;
	}
	
	
	public static String  virtualSystem_NIC_Network(String vs)
	{

		int i= vs.indexOf("<NICsSection>");
		int j=vs.indexOf("</NICsSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Network>");
		 j=ps.indexOf("</Network>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Network>", "");		
		return v;
	}
	
	
	public static String  virtualSystem_NIC_IP(String vs)
	{

		int i= vs.indexOf("<NICsSection>");
		int j=vs.indexOf("</NICsSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<IP>");
		 j=ps.indexOf("</IP>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<IP>", "");		
		return v;
	}
	
	
	
	public static String  virtualSystem_NIC_IP6(String vs)
	{

		int i= vs.indexOf("<NICsSection>");
		int j=vs.indexOf("</NICsSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<IP6>");
		 j=ps.indexOf("</IP6>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<IP6>", "");		
		return v;
	}
	
	
	public static String  virtualSystem_NIC_MAC(String vs)
	{

		int i= vs.indexOf("<NICsSection>");
		int j=vs.indexOf("</NICsSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<MAC>");
		 j=ps.indexOf("</MAC>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<MAC>", "");		
		return v;
	}
	
	
	
	public static String  virtualSystem_OSS_Description(String vs)
	{

		int i= vs.indexOf("<OperatingSystemSection ");
		int j=vs.indexOf("</OperatingSystemSection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Description>");
		 j=ps.indexOf("</Description>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Description>", "");
		
		return v;
	}
	
	
	public static double  get_VHS_Item_CPU(String  vs)
	{
		
		Vector<String> VSs = new Vector<String>();
		String chaine="<Item>";
		double ram =0.0;
		String UN= "hertz";
		while(vs.contains(chaine)){
		int i=vs.indexOf("<Item>");
		int j= vs.indexOf("</Item>");
		String item= vs.substring(i, j);
		VSs.addElement(item);
		vs=vs.replaceFirst("<Item>", "");
		vs=vs.replaceFirst("</Item>", "");
		}
		//vs=vs.replaceAll("<Product>", "");
		for(int i=0; i<VSs.size(); i++)
		if(VSs.get(i).contains(UN) )
		{
			int k=VSs.get(i).indexOf("<rasd:VirtualQuantity>");
			int l= VSs.get(i).indexOf("</rasd:VirtualQuantity>");
			String s= VSs.get(i).substring(k, l);
			 s=s.replaceAll("<rasd:VirtualQuantity>", "");
			 ram=Double.parseDouble(s);
			 return ram;
		}
		return ram;
		}
		
		
	
	
	
	public static int  get_VHS_Item_RAM(String  vs)
	{
		
		Vector<String> VSs = new Vector<String>();
		String chaine="<Item>";
		int ram =0;
		String UN= "byte";
		while(vs.contains(chaine)){
		int i=vs.indexOf("<Item>");
		int j= vs.indexOf("</Item>");
		String item= vs.substring(i, j);
		VSs.addElement(item);
		vs=vs.replaceFirst("<Item>", "");
		vs=vs.replaceFirst("</Item>", "");
		}
		//vs=vs.replaceAll("<Product>", "");
		for(int i=0; i<VSs.size(); i++)
		if(VSs.get(i).contains(UN) )
		{
			
			String xx=VSs.get(i);
			if(xx.contains("<StorageItem>"))
			{
				int x=xx.indexOf("<StorageItem>");
				String f= xx.substring(x, xx.length());
				xx=xx.replaceAll(f, "");
				xx=xx.replaceAll("<StorageItem>", "");
			}
			int k=xx.indexOf("<rasd:VirtualQuantity>");
			int l= xx.indexOf("</rasd:VirtualQuantity>");
			String s= xx.substring(k, l);
			 s=s.replaceAll("<rasd:VirtualQuantity>", "");
			 ram=Integer.parseInt(s);
			 return ram;
		}
		return ram;
		
	}
	
	

	
	public static String  virtualSystem_VHS_StorageItem(String vs)
	{

		int i= vs.indexOf("<StorageItem>");
		int j=vs.indexOf("</StorageItem>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<sasd:Reservation>");
		 j=ps.indexOf("</sasd:Reservation>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<sasd:Reservation>", "");
		
		return v;
	}
	
	public static String create_FileOVF_OneVM(String vs)
	{
		String ovfHead="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"\n<Envelope"+
		"xsi:schemaLocation=\"http://schemas.dmtf.org/ovf/envelope/2 file:///C:/dsp8023_2.0.0_wgv0.9.5.xsd\"" +
		"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ovf=\"http://schemas.dmtf.org/ovf/envelope/2\""+
		"xmlns=\"http://schemas.dmtf.org/ovf/envelope/2\""+
		"xmlns:vssd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_VirtualSystemSettingData\""+
		"xmlns:rasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_ResourceAllocationSettingData\""+
		"xmlns:epasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_EthernetPortAllocationSettingData\""+
		"xmlns:sasd=\"http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_StorageAllocationSettingData\">" +
		"\n<References>"+
		"\n</References>"+
		"\n<UserSection>" +
		 "\n<Login>oneadmin</Login>"+
			"\n<Password>fdf6662f691aab23f3e9e55c3080e4e9</Password>"+			
		"\n</UserSection>\n";
		
		String ovfEnd="</VirtualSystem>"+
				"\n</Envelope>";
		
		String ovf=ovfHead+vs+ovfEnd;
	
		return ovf;
	}
	
	
	
	public static String  getConditionofRule(String vs)
	{

		int i= vs.indexOf("<ElasticitySection>");
		int j=vs.indexOf("</ElasticitySection>");
		 String ps=vs.substring(i,j);
		 i= ps.indexOf("<Rule>");
		 j=ps.indexOf("</Rule>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Rule>", "");
		 
		 i= v.indexOf("<Condition>");
		 j=v.indexOf("</Condition>");
		 String c=v.substring(i, j);
		 c=c.replaceAll("<Condition>", "");
		
		return c;
	}
	
	public static String  getActionofRule(String vs)
	{

		int i= vs.indexOf("<ElasticitySection>");
		int j=vs.indexOf("</ElasticitySection>");
		 String ps=vs.substring(i, j);
		 i= ps.indexOf("<Rule>");
		 j=ps.indexOf("</Rule>");
		 String v=ps.substring(i, j);
		 v=v.replaceAll("<Rule>", "");
		 
		 i= v.indexOf("<Action>");
		 j=v.indexOf("</Action>");
		 String c=v.substring(i, j);
		 c=c.replaceAll("<Action>", "");
		
		return c;
	}
	
	
	
	public static String  getMetric(String conclusion)
	{
		String cpuUsage="cpuUsage";
		String memUsage="memUsage";

		if(conclusion.contains(cpuUsage))
		{
			return cpuUsage;
		}
		
		if(conclusion.contains(memUsage))
		{
			return memUsage;
		}
		return null;
	}
	
	public static boolean  getOperatorGreaterMetric(String condition)
	{
		String greater=">";
		String less="<";
		String equal="==";
		
		String cpuUsage="cpuUsage";
		String memUsage="memUsage";

		if(condition.contains(greater))
		{
			int i= condition.indexOf(greater);
			if(condition.contains(cpuUsage))
			{
				int j= condition.indexOf(cpuUsage);
				if(j<i)
					return true;
			}
			
			if(condition.contains(memUsage))
			{
				int j= condition.indexOf(memUsage);
				if(j<i)
					return true;
			}
					
		}
		return false;
	 }
	
	public static boolean  getOperatorLessMetric(String condition)
	{
		String greater=">";
		String less="<";
		String equal="==";
		
		String cpuUsage="cpuUsage";
		String memUsage="memUsage";

		if(condition.contains(less))
		{
			int i= condition.indexOf(less);
			if(condition.contains(cpuUsage))
			{
				int j= condition.indexOf(cpuUsage);
				if(j<i)
					return true;
			}
			
			if(condition.contains(memUsage))
			{
				int j= condition.indexOf(memUsage);
				if(j<i)
					return true;
			}			
		}
		return false;
	 }
	
	
	/*
	public static boolean  getClientValue(String condition)
	{
		String greater=">";
		String less="<";
		String equal="==";
		
		String cpuUsage="cpuUsage";
		String memUsage="memUsage";
		
		
		String[] chs= condition.split(" ");
		
		for (int i=0; i<chs.length; i++)
		{
			String ch= chs[i];
			
		}

		if(condition.contains(less))
		{
			int i= condition.indexOf(less);
			if(condition.contains(cpuUsage))
			{
				int j= condition.indexOf(cpuUsage);
				if(j<i)
					return true;
			}
			
			if(condition.contains(memUsage))
			{
				int j= condition.indexOf(memUsage);
				if(j<i)
					return true;
			}			
		}
		return false;
	 }
	*/
	
	
	
	public static int extractNumber(String str) 
	{                

	    if(str == null || str.isEmpty()) return 0;

	    StringBuilder sb = new StringBuilder();
	    boolean found = false;
	    for(char c : str.toCharArray()){
	        if(Character.isDigit(c)){
	            sb.append(c);
	            found = true;
	        } else if(found){
	            // If we already found a digit before and this char is not a digit, stop looping
	            break;                
	        }
	    }
  
	    int x= Integer.parseInt(sb.toString());
	    
	    return x;
	}
	
	
	

}
