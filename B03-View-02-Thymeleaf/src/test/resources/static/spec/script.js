//测试类型描述，这里表示测试unitTestApp的controllers
describe('测试配置格式化对象', function() {
 
    beforeEach(module('appService'));
    var lstParamData;
    beforeEach(function () {
        console.log("beforeEach");
        lstParamData = [
                {
                    id: '1',
                    name:'comtop',
                    code:'ctp'
                }, '1'];
    });

    it('简单表达式的格式化配置应该能正确格式化数据', 
        inject(function(ConfigFormatter) {
            var formatText = "组织id=1,组织名=comtop,"
                            +"组织编码=ctp,组织id=1";
            var formatConfig = "组织id={arg1.id},组织名={arg1.name},"
                            +"组织编码={arg1.code},组织id={arg2}";
            expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
        })
    );

    describe('复杂表达式的格式化数据测试', function() {
        it('List<String>数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].lstUseId=['1','2'];

                var formatText = "组织id=1,组织名=comtop,"
                                +'组织编码=ctp,人员ids=["1","2"]';

                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                + "人员ids={arg1.lstUseId}";
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
            })
        );

        it('List<Object>数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].lstUser=[{
                        id:'1',
                        name:'Yancey',
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            lstUser: null
                        }
                    },
                    {
                        id:'2',
                        name:'Tom',
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            lstUser: null
                        }
                    }
                ];

                var formatText = "组织id=1,组织名=comtop,"
                                +"组织编码=ctp,"
                                +'人员列表=[{"人员名":"Yancey","人员id":"1","人员组织":{"人员组织编码":"ctp"}}'
                                +',{"人员名":"Tom","人员id":"2","人员组织":{"人员组织编码":"ctp"}}]';
                var formatConfig = "组织id={arg1.id},组织名={arg1.name},"
                                +"组织编码={arg1.code},"
                                +"人员列表={arg1.lstUser[人员列表,name=人员名,id=人员id,org=人员组织,org.code=人员组织编码]}";
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
            })
        );

        it('List<..List<Object>..>的数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].lstUser=[{
                        id:'1',
                        name:'Yancey',
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            lstUser: [{id:'1',name:'Yancey1'},{id:'2',name:'Yancey2'},{id:'3',name:'Yancey3'},{id:'4',name:'Yancey4'}]
                        }
                    },
                    {
                        id:'2',
                        name:'Tom',
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            lstUser: null
                        }
                    }
                ];

                var formatText = "组织id=1,组织名=comtop,组织编码=ctp,"
                                +'人员列表=['
                                +'{"人员名":"Yancey","人员id":"1","人员组织":{"人员组织编码":"ctp","组织人员列表":[{"人员名":"Yancey1"},{"人员名":"Yancey2"},{"人员名":"Yancey3"},{"人员名":"Yancey4"}]}},'
                                +'{"人员名":"Tom","人员id":"2","人员组织":{"人员组织编码":"ctp","组织人员列表":null}}'
                                +']';

                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                +"人员列表={"
                                +'arg1.lstUser[人员列表,name=人员名,id=人员id,org=人员组织,org.code=人员组织编码],'
                                +'arg1.lstUser.org.lstUser[组织人员列表,name=人员名]'
                                +'}';
                var rFormatConfig = "组织id={result.id},组织名={result.name},组织编码={result.code},"
                                +"人员列表={"
                                +'result.lstUser[人员列表,name=人员名,id=人员id,org=人员组织,org.code=人员组织编码],'
                                +'result.lstUser.org.lstUser[组织人员列表,name=人员名]'
                                +'}';
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
                expect(ConfigFormatter.format(rFormatConfig, lstParamData,lstParamData[0])).toEqual(formatText);
            })
        );

        it('List<List<Object>>的数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].lstUser=[
                    [{id:'1',name:'Yancey1'},{id:'2',name:'Yancey2'}],
                    [{id:'1-1',name:'Yancey1-1'},{id:'2-2',name:'Yancey2-2'}]
                ];

                var formatText = "组织id=1,组织名=comtop,组织编码=ctp,"
                                +'人员列表=['
                                +'[{"人员名":"Yancey1","人员id":"1"},{"人员名":"Yancey2","人员id":"2"}],'
                                +'[{"人员名":"Yancey1-1","人员id":"1-1"},{"人员名":"Yancey2-2","人员id":"2-2"}]'
                                +']';

                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                +"人员列表={"
                                +'arg1.lstUser[人员列表=!,name=人员名,id=人员id]'
                                +'}';
                var rFormatConfig = "组织id={result.id},组织名={result.name},组织编码={result.code},"
                                +"人员列表={"
                                +'result.lstUser[人员列表=!,name=人员名,id=人员id]'
                                +'}';

                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);

                expect(ConfigFormatter.format(rFormatConfig, lstParamData,lstParamData[0])).toEqual(formatText);
            })
        );

        it('List<..List<String>..>的数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].lstUser=[{
                        id:'1',
                        name:'Yancey',
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            userIds: ['1','2','3']
                        }
                    },
                    {
                        id:'2',
                        name:'Tom',
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            userIds: null
                        }
                    }
                ];

                var formatText = "组织id=1,组织名=comtop,组织编码=ctp,"
                                +'人员列表=['
                                +'{"人员名":"Yancey","人员id":"1","人员组织":{"人员组织编码":"ctp","组织人员ids":["1","2","3"]}},'
                                +'{"人员名":"Tom","人员id":"2","人员组织":{"人员组织编码":"ctp","组织人员ids":null}}'
                                +']';

                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                +"人员列表={"
                                +'arg1.lstUser[人员列表,name=人员名,id=人员id,org=人员组织,org.code=人员组织编码,org.userIds=组织人员ids],'
                                +'}';
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
            })
        );

        it('Map<string,string>的数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].mapUser={
                    'id':'1',
                    'name': 'Yancey'
                };

                var formatText = "组织id=1,组织名=comtop,"
                                +"组织编码=ctp,"
                                +'人员列表={"id":"1","name":"Yancey"}';
                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                +"人员列表={arg1.mapUser}";
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
            })
        );

        it('Map<string,Object>的数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].mapUser={'1-1':{
                        id:'1',
                        name:'Yancey',
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp'
                        }
                    },
                    '2-2': {
                        id:'2',
                        name:'Tom',
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp'
                        }
                    }
                };

                var formatText = "组织id=1,组织名=comtop,"
                                +"组织编码=ctp,"
                                +'人员列表={"1-1":{"人员名":"Yancey","人员id":"1","人员组织":{"人员组织编码":"ctp"}}'
                                +',"2-2":{"人员名":"Tom","人员id":"2","人员组织":{"人员组织编码":"ctp"}}}';
                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                +"人员列表={arg1.mapUser[人员列表,name=人员名,id=人员id,org=人员组织,org.code=人员组织编码]}";
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
            })
        );

        it('List<..Map<string,String>..>的数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].lstUser=[{
                        id:'1',
                        name:'Yancey',
                        mapUser: {
                            'id':'1',
                            'name': 'Yancey'
                        },
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            mapUser: {
                                'id':'1',
                                'name': 'Yancey'
                            }
                        }
                    },
                    {
                        id:'2',
                        name:'Tom',
                        mapUser: {
                            'id':'2',
                            'name': 'Tom'
                        },
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            mapUser: {
                                'id':'2',
                                'name': 'Tom'
                            }
                        }
                    }
                ];

                var formatText = "组织id=1,组织名=comtop,组织编码=ctp,"
                                +'人员列表=['
                                +'{"人员名":"Yancey","人员id":"1","人员组织":{"人员组织编码":"ctp","人员map":{"id":"1","name":"Yancey"}},"人员map":{"id":"1","name":"Yancey"}},'
                                +'{"人员名":"Tom","人员id":"2","人员组织":{"人员组织编码":"ctp","人员map":{"id":"2","name":"Tom"}},"人员map":{"id":"2","name":"Tom"}}'
                                +']';

                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                +"人员列表={"
                                +'arg1.lstUser[人员列表,name=人员名,id=人员id,org=人员组织,mapUser=人员map,org.code=人员组织编码,org.mapUser=人员map],'
                                +'}';
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
            })
        );

        it('List<..Map<string,Object>..>的数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].lstUser=[{
                        id:'1',
                        name:'Yancey',
                        mapUser: {
                            'id':'1',
                            'name': 'Yancey'
                        },
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            mapUser: {
                                '1-1':{
                                    id:'1',
                                    name:'Yancey',
                                    org: {
                                        id: '1',
                                        name:'comtop',
                                        code:'ctp'
                                    }
                                },
                                '1-2': {
                                    id:'2',
                                    name:'Tom',
                                    org: {
                                        id: '1',
                                        name:'comtop',
                                        code:'ctp'
                                    }
                                }
                            }
                        }
                    },
                    {
                        id:'2',
                        name:'Tom',
                        mapUser: {
                            'id':'2',
                            'name': 'Tom'
                        },
                        org: {
                            id: '1',
                            name:'comtop',
                            code:'ctp',
                            mapUser: {
                                '2-1':{
                                    id:'1',
                                    name:'Yancey',
                                    org: {
                                        id: '1',
                                        name:'comtop',
                                        code:'ctp'
                                    }
                                },
                                '2-2': {
                                    id:'2',
                                    name:'Tom',
                                    org: {
                                        id: '1',
                                        name:'comtop',
                                        code:'ctp'
                                    }
                                }
                            }
                        }
                    }
                ];

                var formatText = "组织id=1,组织名=comtop,组织编码=ctp,"
                                +'人员列表=['
                                +'{"人员名":"Yancey","人员id":"1","人员组织":{"人员组织编码":"ctp","人员map":{"1-1":{"人员名(map)":"Yancey","组织(map)":{"组织编码(map)":"ctp"}},"1-2":{"人员名(map)":"Tom","组织(map)":{"组织编码(map)":"ctp"}}}},"人员map":{"id":"1","name":"Yancey"}},'
                                +'{"人员名":"Tom","人员id":"2","人员组织":{"人员组织编码":"ctp","人员map":{"2-1":{"人员名(map)":"Yancey","组织(map)":{"组织编码(map)":"ctp"}},"2-2":{"人员名(map)":"Tom","组织(map)":{"组织编码(map)":"ctp"}}}},"人员map":{"id":"2","name":"Tom"}}'
                                +']';

                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                +"人员列表={"
                                +'arg1.lstUser[人员列表,name=人员名,id=人员id,org=人员组织,mapUser=人员map,org.code=人员组织编码,org.mapUser=人员map],'
                                +'arg1.lstUser.org.mapUser[人员map,name=人员名(map),org=组织(map),org.code=组织编码(map)],'
                                +'}';
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
            })
        );

        it('List<Map<string,Object>>的数据应该能正确被格式化', 
            inject(function(ConfigFormatter) {
                lstParamData[0].lstUser=[
                            {
                                '1-1':{
                                    id:'1',
                                    name:'Yancey',
                                    org: {
                                        id: '1',
                                        name:'comtop',
                                        code:'ctp'
                                    }
                                },
                                '1-2': {
                                    id:'2',
                                    name:'Tom',
                                    org: {
                                        id: '1',
                                        name:'comtop',
                                        code:'ctp'
                                    }
                                }
                            },
                            {
                                '2-1':{
                                    id:'1',
                                    name:'Yancey',
                                    org: {
                                        id: '1',
                                        name:'comtop',
                                        code:'ctp'
                                    }
                                },
                                '2-2': {
                                    id:'2',
                                    name:'Tom',
                                    org: {
                                        id: '1',
                                        name:'comtop',
                                        code:'ctp'
                                    }
                                }
                            }
                ];

                var formatText = "组织id=1,组织名=comtop,组织编码=ctp,"
                                +'人员列表=['
                                +'{"1-1":{"人员名(map)":"Yancey","组织(map)":{"组织编码(map)":"ctp"}},"1-2":{"人员名(map)":"Tom","组织(map)":{"组织编码(map)":"ctp"}}},'
                                +'{"2-1":{"人员名(map)":"Yancey","组织(map)":{"组织编码(map)":"ctp"}},"2-2":{"人员名(map)":"Tom","组织(map)":{"组织编码(map)":"ctp"}}}'
                                +']';

                var formatConfig = "组织id={arg1.id},组织名={arg1.name},组织编码={arg1.code},"
                                +"人员列表={"
                                +'arg1.lstUser[人员列表=!,name=人员名(map),org=组织(map),org.code=组织编码(map)],'
                                +'}';
                var rFormatConfig = "组织id={result.id},组织名={result.name},组织编码={result.code},"
                                +"人员列表={"
                                +'result.lstUser[人员列表=!,name=人员名(map),org=组织(map),org.code=组织编码(map)],'
                                +'}';
                expect(ConfigFormatter.format(formatConfig, lstParamData)).toEqual(formatText);
                expect(ConfigFormatter.format(rFormatConfig, lstParamData,lstParamData[0])).toEqual(formatText);
            })
        );
        
    });
    
});